package soa.ejb.stateless;

import soa.ejb.local.UserManager;
import soa.model.entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "UserEJB")
public class UserBean implements UserManager {
    @PersistenceContext(unitName = "twitter")
    private EntityManager em;

    @Override
    public UserEntity findByUsername(String username) {
        TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.findByUsername", UserEntity.class)
                .setParameter("name", username);
        List<UserEntity> results = query.getResultList();
        UserEntity result = null;
        if (!results.isEmpty()) {
            result = results.get(0);
        }
        return result;
    }

    @Override
    public boolean addUser(String username, String mail, String password) {

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setMail(mail);
        em.persist(user);

        return true;
    }
    @Override
    public void deleteUser(int id)
    {
        UserEntity user = em.find(UserEntity.class, id);
        em.remove(user);
    }



}
