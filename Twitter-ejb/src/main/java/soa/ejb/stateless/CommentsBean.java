package soa.ejb.stateless;

import soa.ejb.local.CommentsManager;
import soa.model.entity.CommentEntity;
import soa.model.entity.PostEntity;
import soa.model.entity.UserEntity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by krzysiek on 6/12/15.
 */

@Stateless(name = "CommentsEJB")
@Local(CommentsManager.class)
public class CommentsBean implements CommentsManager{
    @PersistenceContext(unitName = "twitter")
    private EntityManager em;

    @Override
    public List<CommentEntity> getAllCommmentsFromPost(int postid) {

        TypedQuery<CommentEntity> query = em.createNamedQuery("CommentEntity.findAllFromPost",CommentEntity.class)
                .setParameter("postid",postid);
        List<CommentEntity> results = query.getResultList();

        return results;
    }
    @Override
    public void addCommentToPost(int postid, String content,Timestamp creationDate,PostEntity commentedPost,UserEntity author) {

        CommentEntity comment = new CommentEntity();
        comment.setContent(content);
        comment.setCreationDate(creationDate);
        comment.setCommentedPost(commentedPost);
        comment.setAuthor(author);
        PostEntity post = em.find(PostEntity.class, postid);
        post.getComments().add(comment);
        em.persist(comment);
    }
}
