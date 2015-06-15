package soa.ejb.local;

import soa.model.entity.CommentEntity;
import soa.model.entity.PostEntity;
import soa.model.entity.UserEntity;

import javax.ejb.Local;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by krzysiek on 6/12/15.
 */
@Local
public interface CommentsManager {

    List<CommentEntity> getAllCommmentsFromPost(int postid);
    public void addCommentToPost(int postid, String content,Timestamp creationDate,PostEntity commentedPost,UserEntity author);
}






