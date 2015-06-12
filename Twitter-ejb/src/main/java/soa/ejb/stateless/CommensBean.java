package soa.ejb.stateless;

import soa.ejb.local.CommentsManager;
import soa.model.entity.PostEntity;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by krzysiek on 6/12/15.
 */
@Local(CommentsManager.class)
public class CommensBean  implements CommentsManager{
    @PersistenceContext(unitName = "twitter")
    private EntityManager em;

    @Override
    public PostEntity getCommentedPost() {
        return null;
    }
}
