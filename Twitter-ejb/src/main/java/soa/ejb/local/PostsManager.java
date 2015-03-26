package soa.ejb.local;

import soa.model.entity.PostEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PostsManager {
    List<PostEntity> getAllPosts();
}
