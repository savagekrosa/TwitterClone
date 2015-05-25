package soa.ejb.local;

import soa.model.entity.HashtagEntity;
import soa.model.entity.PostEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.regex.Pattern;

@Local
public interface HashtagManager {
    Pattern HASHTAG_PATTERN = Pattern.compile("#(\\w+|\\W+)");

    List<PostEntity> getPostsTaggedWith(String hashtag);

    void saveTagsOfPost(PostEntity post);

    HashtagEntity getHastagForTag(String tag);
}
