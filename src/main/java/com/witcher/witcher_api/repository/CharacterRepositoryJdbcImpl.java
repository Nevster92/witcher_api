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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
    public Integer insertCharacter(String characterName, String userId) {
        String sql = "INSERT INTO character (name, user_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
           jdbcTemplate.update(connection -> {
               PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
               ps.setString(1, characterName);
               ps.setString(2, userId);
               return ps;
           }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        int generatedId = ((Number) keys.get("id")).intValue();

        return generatedId;
    }


    @Override
    public void deleteAttrubute(String attributeTable, int characterId) {
        String sql = "DELETE FROM "+attributeTable+" WHERE character_id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, characterId);
            return ps;
        });
    }

    @Override
    public void deleteCharacter(int characterId) {
        String sql = "DELETE FROM character WHERE id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, characterId);
            return ps;
        });
    }

    @Override
    public void insertAttribute(String attributeTable, int characterId) {
        String sql = "INSERT INTO "+ attributeTable +" (character_id) VALUES (?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, characterId);
            return ps;
        });
    }



    @Override
    public boolean hasAccesToCharacter(String userId, int characterId) {
        String sql = "SELECT 1 FROM character WHERE user_id = ? AND id = ?";
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
