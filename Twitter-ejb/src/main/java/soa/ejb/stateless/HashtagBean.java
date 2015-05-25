package soa.ejb.stateless;

import soa.ejb.local.HashtagManager;
import soa.model.entity.HashtagEntity;
import soa.model.entity.PostEntity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.regex.Matcher;

@Local(HashtagManager.class)
@Stateless(name = "HashtagEJB")
public class HashtagBean implements HashtagManager {
    @PersistenceContext(unitName = "twitter")
    private EntityManager em;

    @Override
    public List<PostEntity> getPostsTaggedWith(String hashtag) {
        HashtagEntity hashtagEntity = getHastagForTag(hashtag);
        if (hashtagEntity != null)
            return hashtagEntity.getTaggedPosts();
        return null;
    }

    @Override
    public HashtagEntity getHastagForTag(String tag) {
        TypedQuery<HashtagEntity> query = em.createNamedQuery("HashtagEntity.findByTag", HashtagEntity.class)
                .setParameter("tag", tag);
        List<HashtagEntity> results = query.getResultList();
        HashtagEntity result = null;
        if (!results.isEmpty()) {
            result = results.get(0);
        }
        return result;
    }

    @Override
    public void saveTagsOfPost(PostEntity post) {
        System.out.println("idididi: " + post.getPostId());
        Matcher mat = HASHTAG_PATTERN.matcher(post.getContent());
        while (mat.find()) {
            String foundTag = mat.group(1);
            HashtagEntity matchedHashtag = getHastagForTag(foundTag);
            if (matchedHashtag == null) {
                matchedHashtag = new HashtagEntity();
                matchedHashtag.setHashtag(foundTag);
            }
            matchedHashtag.getTaggedPosts().add(post);
            post.getHashtags().add(matchedHashtag);
            em.merge(matchedHashtag);
            em.merge(post);
        }
    }
}
