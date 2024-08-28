package com.witcher.witcher_api;
import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.model.pojo.ReflexSkill;
import com.witcher.witcher_api.model.pojo.User;
import com.witcher.witcher_api.repository.CharacterRepository;
import com.witcher.witcher_api.repository.UserRepositoryJdbcImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

// import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CharacterRepoTest {


    @Autowired
    private CharacterRepository characterRepositoryJdbcImpl;


    @Autowired
    private UserRepositoryJdbcImpl userRepositoryJdbcImpl;

    private int testCharacterId01;
    private int testCharacterId02;

    User user_01 = new User("user_id_01", "user_username_01", "user_email_email_01");
    User user_02 = new User("user_id_02", "user_username_02", "user_email_email_02");

    @BeforeEach
    @Transactional
    public  void inic(){
        userRepositoryJdbcImpl.insertUser(user_01);
        userRepositoryJdbcImpl.insertUser(user_02);

        Character character_01 = new Character();
        character_01.setName("character_01");
        character_01.setUser(user_01);
        character_01.setEmp(3);
        character_01.setBody(12);
        character_01.setDex(8);
        character_01.setHp(10);
        character_01.setEnc(90);
        character_01.setAge(33);

        Character character_02 = new Character();
        character_02.setName("character_02");
        character_02.setUser(user_01);
        character_02.setEmp(3);
        character_02.setBody(12);
        character_02.setDex(8);
        character_02.setHp(10);
        character_02.setEnc(90);
        character_02.setAge(33);

        this.testCharacterId01 = characterRepositoryJdbcImpl.insertCharacter(character_01.getName(), user_01.getId());
        characterRepositoryJdbcImpl.insertAttribute("body_skill", this.testCharacterId01);
        characterRepositoryJdbcImpl.insertAttribute("craft_skill", this.testCharacterId01);
        characterRepositoryJdbcImpl.insertAttribute("dexterity_skill", this.testCharacterId01);
        characterRepositoryJdbcImpl.insertAttribute("empathy_skill", this.testCharacterId01);
        characterRepositoryJdbcImpl.insertAttribute("intelligence_skill", this.testCharacterId01);
        characterRepositoryJdbcImpl.insertAttribute("reflex_skill", this.testCharacterId01);
        characterRepositoryJdbcImpl.insertAttribute("will_skill", this.testCharacterId01);


        this.testCharacterId02 = characterRepositoryJdbcImpl.insertCharacter(character_02.getName(), user_02.getId());
        characterRepositoryJdbcImpl.insertAttribute("body_skill", this.testCharacterId02);
        characterRepositoryJdbcImpl.insertAttribute("craft_skill", this.testCharacterId02);
        characterRepositoryJdbcImpl.insertAttribute("dexterity_skill", this.testCharacterId02);
        characterRepositoryJdbcImpl.insertAttribute("empathy_skill", this.testCharacterId02);
        characterRepositoryJdbcImpl.insertAttribute("intelligence_skill", this.testCharacterId02);
        characterRepositoryJdbcImpl.insertAttribute("reflex_skill", this.testCharacterId02);
        characterRepositoryJdbcImpl.insertAttribute("will_skill", this.testCharacterId02);
    }



    @Test
    @Transactional
    public void testFindCharacter(){
        Character testCharacter = characterRepositoryJdbcImpl.findCharacterById(testCharacterId01);
        assertEquals( "Find character by id","character_01", testCharacter.getName());
        Assertions.assertThrows(Exception.class, () -> {
            characterRepositoryJdbcImpl.findCharacterById(0);
        });

    }

    @Test
    @Transactional
    public void testSaveCharacter() {
        Character testCharacter = new Character();
        testCharacter.setName("testCharacter");
        testCharacterId01 = characterRepositoryJdbcImpl.insertCharacter(testCharacter.getName(), user_01.getId());

        Character characterFromRepository = characterRepositoryJdbcImpl.findCharacterById(testCharacterId01);
        assertEquals( "Get back the same character as saved","testCharacter", characterFromRepository.getName());
    }

    @Test
    @Transactional
    public void testFindAllCharacters() {
        Character testCharacter = new Character();
        testCharacter.setName("testCharacter");
        testCharacterId01 = characterRepositoryJdbcImpl.insertCharacter(testCharacter.getName(), user_01.getId());

        List<Character> characters = characterRepositoryJdbcImpl.findAllCharacters(user_01.getId());
        assertEquals("Size of the List is not correct",2, characters.size());
        assertTrue("The list should contain a character named testCharacter.", characters.stream().anyMatch(c -> c.getName().equals("testCharacter")));
        assertTrue("The list should contain a character named character_01.", characters.stream().anyMatch(c -> c.getName().equals("character_01")));

        assertEquals("The list size must be 0 with not existing userId.",0, characterRepositoryJdbcImpl.findAllCharacters("notExistedUserId").size());
    }

    @Test
    @Transactional
    public void testUpdateCharacterSkills() {
        ReflexSkill reflexSkill = new ReflexSkill();
        reflexSkill.setBrawling(3);
        reflexSkill.setDodge(5);
        reflexSkill.setMeele(8);
        reflexSkill.setSmall_blades(12);

        characterRepositoryJdbcImpl.updateCharacterSkills(testCharacterId01, reflexSkill, "reflex_skill");
        Character testCharacter = characterRepositoryJdbcImpl.findCharacterById(testCharacterId01);

        assertEquals("Brawling skill Update not correct",3, testCharacter.getReflexSkill().getBrawling());
        assertEquals("Dodge skill Update not correct",5, testCharacter.getReflexSkill().getDodge());
        assertEquals("Meele skill Update not correct",8, testCharacter.getReflexSkill().getMeele());
        assertEquals("Small blades skill Update not correct",12, testCharacter.getReflexSkill().getSmall_blades());
    }

    @Test
    @Transactional
    public void insertCharacter(){
       Integer newCharacterId =  characterRepositoryJdbcImpl.insertCharacter("insertTestName", this.user_01.getId());
        assertEquals("Cannot find the new Character by Id.", "insertTestName", characterRepositoryJdbcImpl.findCharacterById(newCharacterId).getName());
        }





}
