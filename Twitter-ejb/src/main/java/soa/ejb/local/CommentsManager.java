package soa.ejb.local;
import soa.model.entity.CommentEntity;
import soa.model.entity.PostEntity;

import javax.ejb.Local;
/**
 * Created by krzysiek on 6/12/15.
 */
@Local
public interface CommentsManager {
    PostEntity getCommentedPost(String commentId);
    boolean addCommentToPost(int postid, CommentEntity comment);


}






