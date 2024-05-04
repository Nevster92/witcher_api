package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.*;
import com.witcher.witcher_api.model.pojo.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterRepositoryJdbcImpl implements CharacterRepository{

    private JdbcTemplate jdbc;

    @Autowired
    public CharacterRepositoryJdbcImpl(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    public String test(){
        return "lófasz";
    }

    @Override
    public Character findCharacterById(int id) {
        //TODO refactor to single sql query
        String characterSql = "SELECT * FROM character WHERE id = ?";

        Character character = (Character) jdbc.queryForObject(
                characterSql,
                new Object[]{id},
                new BeanPropertyRowMapper(Character.class));

        try {
            String bodySql = "SELECT * FROM body_skill WHERE character_id = ?";
            character.setBodySkill((BodySkill) jdbc.queryForObject(bodySql, new Object[]{id}, new BeanPropertyRowMapper(BodySkill.class)));
        }catch (EmptyResultDataAccessException e){}

        try{
            String dexteritySql = "SELECT * FROM dexterity_skill WHERE character_id = ?";
            character.setDexteritySkill((DexteritySkill) jdbc.queryForObject(dexteritySql,new Object[]{id},new BeanPropertyRowMapper(DexteritySkill.class)));
        }catch (EmptyResultDataAccessException e){}

        try {
           String intelligenceSql = "SELECT * FROM intelligence_skill WHERE character_id = ?";
            character.setIntelligenceSkill((IntelligenceSkill) jdbc.queryForObject(intelligenceSql,new Object[]{id},new BeanPropertyRowMapper(IntelligenceSkill.class)));
        }catch (EmptyResultDataAccessException e){}

        try {
            String empathySkillSql = "SELECT * FROM empathy_skill WHERE character_id = ?";
            character.setEmpathySkill((EmpathySkill) jdbc.queryForObject(empathySkillSql,new Object[]{id},new BeanPropertyRowMapper(EmpathySkill.class)));
        }catch (EmptyResultDataAccessException e){}

        try {
            String craftSkillSql = "SELECT * FROM craft_skill WHERE character_id = ?";
            character.setCraftSkill((CraftSkill) jdbc.queryForObject(craftSkillSql,new Object[]{id},new BeanPropertyRowMapper(CraftSkill.class)));
        }catch (EmptyResultDataAccessException e){}

        return character;
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
}
