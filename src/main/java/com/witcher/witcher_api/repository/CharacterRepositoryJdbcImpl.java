package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.utils.SqlPrameter;
import com.witcher.witcher_api.utils.Utils;
import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.model.request.CharacterRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

    private NamedParameterJdbcTemplate jdbcNamed;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CharacterRepositoryJdbcImpl(NamedParameterJdbcTemplate jdbcNamed, JdbcTemplate jdbcTemplate){
        this.emf = Persistence.createEntityManagerFactory("wticher-pg");
        // TODO Adott metódusokba kihelyezni mert nem thread safe
        this.entityManager = emf.createEntityManager();
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcNamed = jdbcNamed;
    }
    public String test(){
        return "tesztiiii";
    }

    @Override
    public Character findCharacterById(int id) {
        Character character = entityManager.find(Character.class, id);
        entityManager.refresh(character);
        character.initializeStats();
        return  character;
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
    public Character updateCharacterSkills(int characterId, Object skills, String tableName) {
        SqlPrameter utilSql = new Utils().sqlUpdateBuilder(skills, tableName, characterId, "character_id");
        jdbcNamed.update(utilSql.getSqlQuery(), utilSql.getParameters());
        return findCharacterById(characterId);
    }

    @Override
    public Character updateCharacterCore(int characterId, Object skills, String tableName) {
        SqlPrameter utilSql = new Utils().sqlUpdateBuilder(skills, tableName, characterId, "id");
        jdbcNamed.update(utilSql.getSqlQuery(), utilSql.getParameters());
        return findCharacterById(characterId);
    }


    @Override
    public boolean hasAccesToCharacter(String userId, int characterId) {
        String sql = "SELECT 1 FROM user_characters WHERE user_id = ? AND character_id = ?";
        try {
            jdbcTemplate.queryForObject(sql, Integer.class, userId, characterId);
            return true;
        }catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public Character test(int id){
        String characterSql = "SELECT * FROM character WHERE id = ?";

        Character character = (Character) jdbcTemplate.queryForObject(
                characterSql,
                new Object[]{id},
                new BeanPropertyRowMapper(Character.class));
        return character;
    }
}
