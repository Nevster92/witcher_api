package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class UserRepositoryHibernateImpl  implements UserRepository
{

    private EntityManagerFactory emf;
    private EntityManager entityManager;
    // private EntityManager entityManager = Persistence.createEntityManagerFactory("wticher-pg").createEntityManager();
    public UserRepositoryHibernateImpl() {
        this.emf = Persistence.createEntityManagerFactory("wticher-pg");
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public User getUserById(String userId) {
        return entityManager.find(User.class,userId);
    }

    @Override
    public void setUsernameById(String userId, String userName) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
            user.setUsername(userName);
            entityManager.merge(user);
            entityManager.getTransaction().commit();
    }

    @Override
    public void setEmailById(String userId, String email) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        user.setEmail(email);
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }
}
