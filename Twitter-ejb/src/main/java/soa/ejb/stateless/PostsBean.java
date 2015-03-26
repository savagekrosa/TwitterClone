package soa.ejb.stateless;

import soa.ejb.local.PostsManager;
import soa.model.entity.PostEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "PostsEJB")
public class PostsBean implements PostsManager {
    @PersistenceContext(unitName = "twitter")
    private EntityManager em;

    public List<PostEntity> getAllPosts() {
        return em.createQuery("select post from PostEntity post", PostEntity.class).getResultList();
    }
}
