package com.witcher.witcher_api;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.witcher.witcher_api.controller.CharacterController;
import com.witcher.witcher_api.model.pojo.BodySkill;
import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.repository.CharacterRepo;
import com.witcher.witcher_api.service.CharacterService;
import com.witcher.witcher_api.service.PermissionService;
import com.witcher.witcher_api.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters  = false) // turn off security
@Transactional
public class HibernateTest {


    @Autowired
    private CharacterRepo characterRepo;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PermissionService permissionService;

    @MockBean
    private UserService userService;


    @Test
    public void updateCharacterAttribute() throws Exception {
        Mockito.doNothing().when(permissionService).characterPermission(155L);
        ObjectMapper objectMapper = new ObjectMapper();

        String URI = "/character/{characterId}";
        Long characterId = 155L;

        Character parameterCharacter = new Character();
        parameterCharacter.setName("ModifiedName");
        parameterCharacter.setAge(99);
        parameterCharacter.setBodySkill(new BodySkill());
        parameterCharacter.getBodySkill().setPhysique(111);

        MvcResult result = mockMvc.perform(put(URI, characterId)
                        .content(objectMapper.writeValueAsString(parameterCharacter))  // Set the body here
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus(), "HTTP Code is not OK");

        String jsonResponse = result.getResponse().getContentAsString();
        Character returnedCharacter = objectMapper.readValue(jsonResponse, Character.class);

        assertEquals( "ModifiedName",returnedCharacter.getName(),"The name attribute is not saved");
        assertEquals( 111,returnedCharacter.getBodySkill().getPhysique(),"The (complex) bodySkill.physique attribute is not saved!");

//
//        MvcResult result = mockMvc.perform(get("/test/{characterId}", characterId)
//                        .contentType(MediaType.APPLICATION_JSON)).andReturn();
    }


    @Test
    public void getCharacter() throws Exception {
        Mockito.doNothing().when(permissionService).characterPermission(155L);
        ObjectMapper objectMapper = new ObjectMapper();

        String URI = "/character/{characterId}";
        Long characterId = 155L;

        MvcResult result = mockMvc.perform(get(URI, characterId)
                        .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200, result.getResponse().getStatus(), "HTTP Code is not OK!");

        String jsonResponse = result.getResponse().getContentAsString();

        Character returnedCharacter = objectMapper.readValue(jsonResponse, Character.class);

        assertEquals( "Elso Test Karakter",returnedCharacter.getName(),"The name attribute is not matched!");
        assertEquals( "Armored Hood",returnedCharacter.getHead().getName(),"The head armor is not matched!");
        assertEquals( "Arming Sword",returnedCharacter.getL_arm().getName(),"The l_arm weapon is not matched!");
    }

    @Test
    public void getCharacterList() throws Exception {
        Mockito.when(permissionService.getUserId()).thenReturn("10");

        ObjectMapper objectMapper = new ObjectMapper();

        String URI = "/characters";
        MvcResult result = mockMvc.perform(get(URI)
                .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus(), "HTTP Code is not OK!");

        // Necessary because of the UTF-8 encoding
        String jsonResponse = new String(result.getResponse().getContentAsByteArray(), StandardCharsets.UTF_8);
        List<Character> returnedCharacters = objectMapper.readValue(jsonResponse, new TypeReference<List<Character>>() {});

        assertEquals( 2, returnedCharacters.size(),"The list size is not correct!");
        assertEquals( "Elso Test Karakter", returnedCharacters.getFirst().getName(),"The first element of the list is not correct!");
        assertEquals( "Második Test Karakter", returnedCharacters.get(1).getName(),"The second element of the list is not correct!");

    }

    @Test
    public void createNewCharacter() throws Exception {
        Mockito.when(permissionService.getUserId()).thenReturn("10");
        Mockito.when(userService.getUserId()).thenReturn("10");

        ObjectMapper objectMapper = new ObjectMapper();

        Character parameterCharacter = new Character();
        parameterCharacter.setName("New Test Character");
        parameterCharacter.setAge(99);
        parameterCharacter.setProfession("Mage");
        parameterCharacter.setGender("male");
        parameterCharacter.setRace("dwarf");
        parameterCharacter.setBodySkill(new BodySkill());
        parameterCharacter.getBodySkill().setPhysique(111);


        String URI = "/character/create";
        MvcResult result = mockMvc.perform(put(URI)
                        .content(objectMapper.writeValueAsString(parameterCharacter))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus(), "HTTP Code is not OK!");

        String jsonResponse = result.getResponse().getContentAsString();
        Character returnedCharacter = objectMapper.readValue(jsonResponse, Character.class);

        assertEquals("New Test Character", characterRepo.findById(returnedCharacter.getId()).get().getName(), "Cant find the new character");
        assertEquals("New Test Character", returnedCharacter.getName(), "The character name is not correct!");
    }


    @Test
    public void deleteCharacter() throws Exception {
        Mockito.when(userService.getUserId()).thenReturn("10");
        String URI = "/character/delete/{characterId}";
        Long characterId = 155L;

        MvcResult result = mockMvc.perform(delete(URI, characterId)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        assertEquals(200, result.getResponse().getStatus(), "HTTP Code is not OK!");

        assertThrows(NoSuchElementException.class, ()->{
            characterRepo.findById(155L).get();
        }, "The character is still in the database!");
    }



}
