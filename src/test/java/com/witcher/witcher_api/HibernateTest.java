package com.witcher.witcher_api;


import com.witcher.witcher_api.controller.CharacterController;
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
    @Transactional
    public void test() throws Exception {

        Mockito.doNothing().when(permissionService).characterPermission(155L);

        String URI = "/test/{characterId}";
        String body = """
                {
                    "name": "Módosított",
                    "user": {
                        "username": "ModosítottUsername"
                    },
                    "bodyskill": {
                        "physique": 99
                    }
                }
                """;
        Long characterId = 155L;

        MvcResult result = mockMvc.perform(get("/test/{characterId}", characterId)
                        .contentType(MediaType.APPLICATION_JSON)).andReturn();

        System.out.println("NA EZ JÖN VISTZA:");
        System.out.println(result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());


    }

    @Test
    @Transactional
    public void test2() throws Exception {

        Character character = characterRepo.findById(155L).get();
        System.out.println(character.getName());

    }



}
