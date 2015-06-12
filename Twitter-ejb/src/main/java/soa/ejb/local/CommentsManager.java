package soa.ejb.local;
import soa.model.entity.HashtagEntity;
import soa.model.entity.PostEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.regex.Pattern;
/**
 * Created by krzysiek on 6/12/15.
 */
@Local
public interface CommentsManager {
    PostEntity getCommentedPost();

}






