package com.witcher.witcher_api;


import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.repository.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
public class HibernateTest {


    @Autowired
    private CharacterRepo characterRepo;

    @Test
    @Transactional
    public void test() {
        Character character = characterRepo.findByIdCustom(155L);
        System.out.println("Mentés elött: " + character.getName());
        character.setName("LÓFaszjankó");
        characterRepo.save(character);
        Character character2 = characterRepo.findByIdCustom(155L);
        System.out.println("Mentés után: " + character2.getName());
    }


    @Test
    @Transactional
    public void test2() {
        Character character = characterRepo.findByIdCustom(155L);
        System.out.println("Teljse eredeti:");
        System.out.println(character);

        System.out.println("Weapon eredeti:");
        System.out.println(character.getL_arm());

        character.getL_arm().setCost(9999);
        characterRepo.flush();

        System.out.println("Után: " + character.getL_arm().getCost());



    }



}
