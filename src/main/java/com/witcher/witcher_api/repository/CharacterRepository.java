package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.Character;

public interface CharacterRepository {

Character findCharacterById(int id);

 boolean hasAccesToCharacter(String userId, int characterId);

 String test();
}
