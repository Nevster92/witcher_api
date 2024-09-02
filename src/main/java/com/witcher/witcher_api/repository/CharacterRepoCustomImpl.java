package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.Character;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Component
@Repository
public class CharacterRepoCustomImpl implements CharacterRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Character findByIdCustom(Long id) {

        return entityManager.find(Character.class, id);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }


    @Override
    public void testThing(int id) {
        System.out.println("testThing: " + id);
    }


}
