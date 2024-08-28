package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.Character;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Component
@Repository
public class CharacterRepositoryHibernateImpl implements CharacterRepository{
    @Override
    public Character findCharacterById(int id) {
        return null;
    }

    @Override
    public List<Character> findAllCharacters(String userId) {
        return List.of();
    }

    @Override
    public Character updateCharacterSkills(int characterId, Object skills, String tableName) {
        return null;
    }

    @Override
    public Character updateCharacterCore(int characterId, Object skills, String tableName) {
        return null;
    }

    @Override
    public Integer insertCharacter(String characterName, String userId) {
        return 0;
    }

    @Override
    public void insertAttribute(String attributeTable, int characterId) {

    }

    @Override
    public void deleteAttributete(String attributeTable, int characterId) {

    }

    @Override
    public void deleteCharacter(int characterId) {

    }

    @Override
    public boolean hasAccessToCharacter(String userId, int characterId) {
        return false;
    }

    @Override
    public void setWeaponToNull(Integer weaponId, int characterId) {

    }

    @Override
    public void setArmorToNull(Integer armorId, int characterId) {

    }

    @Override
    public void updateRigthArm(int weaponId, int characterId) {

    }

    @Override
    public void updateLeftArm(int weaponIdId, int characterId) {

    }

    @Override
    public String test() {
        return "Hibernate";
    }
}
