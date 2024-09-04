package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.User;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
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
    public void updateUser(String userId, User user) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("update User SET username = CASE WHEN :username IS NOT NULL THEN :username ELSE username END, email = CASE WHEN :email IS NOT NULL THEN :email ELSE email END WHERE id = :id")
                .setParameter("username", user.getUsername())
                .setParameter("email", user.getEmail())
                .setParameter("id", userId)
                .executeUpdate();
        entityManager.getTransaction().commit();


//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaUpdate<User> update = criteriaBuilder.createCriteriaUpdate(User.class);
//        Root<User> root = update.from(User.class);
//
//        update.set(root.get("username"), user.getUsername());
//        update.where(criteriaBuilder.equal(root.get("id"), userId));
//
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.createQuery(update).executeUpdate();
//        transaction.commit();

}

    @Override
    public User getUserById(String userId) {
        return entityManager.find(User.class,userId);
    }


}
