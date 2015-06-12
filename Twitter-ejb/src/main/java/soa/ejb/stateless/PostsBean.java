package soa.ejb.stateless;

import soa.ejb.local.HashtagManager;
import soa.ejb.local.PostsManager;
import soa.model.entity.CommentEntity;
import soa.model.entity.PostEntity;
import soa.model.entity.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Local(PostsManager.class)
@Stateless(name = "PostsEJB")
public class PostsBean implements PostsManager {
    @PersistenceContext(unitName = "twitter")
    private EntityManager em;
    @EJB
    HashtagManager hashtagManager;

    public List<PostEntity> getAllPosts() {
        TypedQuery<PostEntity> query = em.createNamedQuery("PostEntity.findAll", PostEntity.class);
        return query.getResultList();
    }

    @Override
    public void addCommentToPost(int postid, CommentEntity comment) {

        PostEntity post = em.find(PostEntity.class, postid);
        post.getComments().add(comment);
    }

    @Override
    public void savePost(String content, UserEntity author) {
        PostEntity post = new PostEntity();
        post.setAuthor(author);
        post.setContent(content);
        em.persist(post);
        em.flush();
        hashtagManager.saveTagsOfPost(post);
    }
}
