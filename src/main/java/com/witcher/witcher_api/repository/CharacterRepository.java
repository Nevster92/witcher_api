package com.witcher.witcher_api.repository;
import com.witcher.witcher_api.model.pojo.Character;

import java.util.List;

public interface CharacterRepository {

 Character findCharacterById(int id);
 List<Character> findAllCharacters( String userId);
 Character updateCharacterSkills(int characterId, Object skills, String tableName);
 Character updateCharacterCore(int characterId, Object skills, String tableName);

 Integer insertCharacter(String characterName, String userId);
 void insertAttribute(String attributeTable, int characterId);

 void deleteAttributete(String attributeTable, int characterId);
 void deleteCharacter(int characterId);

 boolean hasAccessToCharacter(String userId, int characterId);

 public void setWeaponToNull(Integer weaponId, int characterId);
 public void setArmorToNull(Integer armorId, int characterId);

 public void updateRigthArm(int weaponId, int characterId);
 public void updateLeftArm(int weaponIdId, int characterId);

 String test();
}
