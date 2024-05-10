package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.model.request.CharacterRequest;

import java.util.List;

public interface CharacterRepository {

 Character findCharacterById(int id);
 List<Character> findAllCharacters(CharacterRequest characterRequest, String userId);
 Character editCharacter(int characterId);

 boolean hasAccesToCharacter(String userId, int characterId);


 String test();
}
