package soa.ejb.local;

import soa.model.entity.PostEntity;
import soa.model.entity.UserEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PostsManager {
    List<PostEntity> getAllPosts();

    void savePost(String content, UserEntity author);
}
