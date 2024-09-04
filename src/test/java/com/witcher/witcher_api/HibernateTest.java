package com.witcher.witcher_api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.witcher.witcher_api.controller.CharacterController;
import com.witcher.witcher_api.model.pojo.BodySkill;
import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.repository.CharacterRepo;
import com.witcher.witcher_api.service.CharacterService;
import com.witcher.witcher_api.service.PermissionService;
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


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters  = false) // turn off security
@Transactional
public class HibernateTest {


    @Autowired
    private CharacterRepo characterRepo;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PermissionService permissionService;



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

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URI, characterId)
                        .content(objectMapper.writeValueAsString(parameterCharacter))  // Set the body here
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        Character returnedCharacter = objectMapper.readValue(jsonResponse, Character.class);

        assertEquals(200, result.getResponse().getStatus(), "HTTP Code is not OK");
        assertEquals( "ModifiedName",returnedCharacter.getName(),"The name attribute is not saved");
        assertEquals( 111,returnedCharacter.getBodySkill().getPhysique(),"The (complex) bodySkill.physique attribute is not saved!");

//
//        MvcResult result = mockMvc.perform(get("/test/{characterId}", characterId)
//                        .contentType(MediaType.APPLICATION_JSON)).andReturn();


    }




}
