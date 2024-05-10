package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.*;
import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.model.request.CharacterRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class CharacterRepositoryJdbcImpl implements CharacterRepository{

    private EntityManagerFactory emf;
    private EntityManager entityManager;
    // private EntityManager entityManager = Persistence.createEntityManagerFactory("wticher-pg").createEntityManager();
//    public CharacterRepositoryJdbcImpl() {
//        this.emf = Persistence.createEntityManagerFactory("wticher-pg");
//        this.entityManager = emf.createEntityManager();
//    }

    private JdbcTemplate jdbc;

    @Autowired
    public CharacterRepositoryJdbcImpl(JdbcTemplate jdbc){
        this.emf = Persistence.createEntityManagerFactory("wticher-pg");
        this.entityManager = emf.createEntityManager();
        this.jdbc = jdbc;
    }
    public String test(){
        return "lófasz";
    }

    @Override
    public Character findCharacterById(int id) {
        //TODO refactor to single sql query

        return  entityManager.find(Character.class,id);
    }

    @Override
    public  List<Character> findAllCharacters(CharacterRequest characterRequest, String userId) {
        // The order of the condition has to be the same. First ILIKE and after the NULL check. (Hibernate v6 thing)
    String query = """
           SELECT c 
           FROM Character c WHERE c.user.id = :userId
            AND (LOWER(c.name)  ILIKE CONCAT('%', LOWER(:name ), '%') OR :name IS NULL  )
             AND (  LOWER(c.gender) ILIKE CONCAT('%', LOWER(:gender), '%') OR :gender IS NULL )
             AND (  LOWER(c.profession) ILIKE CONCAT('%', LOWER(:profession), '%') OR :profession IS NULL )
            """;
    try {
        List<Character> characters = entityManager.createQuery(query, Character.class)
                .setParameter("userId", userId)
                .setParameter("name", characterRequest.getName())
                .setParameter("gender", characterRequest.getGender())
                .setParameter("profession", characterRequest.getProfession())
                .getResultList();

        return characters;
    }catch (Exception e){
        System.out.println(e);
    }

    return null;
    }

    @Override
    public Character editCharacter(int characterId) {
        return null;
    }


    @Override
    public boolean hasAccesToCharacter(String userId, int characterId) {
        String sql = "SELECT 1 FROM user_characters WHERE user_id = ? AND character_id = ?";

        try {
            jdbc.queryForObject(sql, Integer.class, userId, characterId);
            return true;
        }catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public Character test(int id){
        String characterSql = "SELECT * FROM character WHERE id = ?";

        Character character = (Character) jdbc.queryForObject(
                characterSql,
                new Object[]{id},
                new BeanPropertyRowMapper(Character.class));
        return character;
    }
}
