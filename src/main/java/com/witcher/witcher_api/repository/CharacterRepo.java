package com.witcher.witcher_api.repository;

import com.witcher.witcher_api.model.pojo.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepo extends JpaRepository<Character, Long> , CharacterRepoCustom{


}
