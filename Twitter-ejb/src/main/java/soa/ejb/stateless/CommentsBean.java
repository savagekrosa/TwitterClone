package soa.ejb.stateless;

import soa.ejb.local.CommentsManager;
import soa.model.entity.CommentEntity;
import soa.model.entity.PostEntity;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by krzysiek on 6/12/15.
 */
@Local(CommentsManager.class)
public class CommentsBean implements CommentsManager{
    @PersistenceContext(unitName = "twitter")
    private EntityManager em;

    @Override
    public PostEntity getCommentedPost(String commentId) {
        TypedQuery<PostEntity> query  = em.createNamedQuery("CommentEntity.findPost",PostEntity.class).setParameter("commentId",commentId);
        List<PostEntity> results = query.getResultList();

        PostEntity result = null;
        if (!results.isEmpty()) {
            result = results.get(0);
        }
        return result;
    }

    @Override
    public boolean addCommentToPost(int postid, CommentEntity comment) {
        return false;
    }
}
