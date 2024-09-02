package com.witcher.witcher_api.repository;


import com.witcher.witcher_api.model.pojo.Character;
import org.springframework.transaction.annotation.Transactional;

public interface CharacterRepoCustom {

    void testThing(int id);

    Character findByIdCustom(Long id);

    void flush();
}
