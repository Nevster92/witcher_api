package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.BodySkill;
import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.model.pojo.IntelligenceSkill;
import com.witcher.witcher_api.model.request.CharacterRequest;

import java.util.List;

public interface CharacterRepository {

 Character findCharacterById(int id);
 List<Character> findAllCharacters(CharacterRequest characterRequest, String userId);
 Character editCharacter(int characterId);
 Character updateCharacterSkills(int characterId, Object skills, String tableName);
 Character updateCharacterCore(int characterId, Object skills, String tableName);

 Integer insertCharacter(String characterName, String userId);
 void insertAttribute(String attributeTable, int characterId);

 void deleteAttrubute(String attributeTable, int characterId);
 void deleteCharacter(int characterId);

 boolean hasAccesToCharacter(String userId, int characterId);


 String test();
}
