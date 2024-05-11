package com.witcher.witcher_api.service;

import com.witcher.witcher_api.model.pojo.BodySkill;
import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.model.request.CharacterRequest;
import com.witcher.witcher_api.repository.CharacterRepository;
import com.witcher.witcher_api.repository.UserRepository;
import jakarta.persistence.Cache;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor

public class CharacterService {


    @Autowired
    CharacterRepository characterRepositoryJdbcImpl;

    @Autowired
    PermissionService permissionService;

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt token = (Jwt) authentication.getPrincipal();
        return  token.getClaims().get("sub").toString();
    }

    public Character getCharacterById(int characterId) throws Exception {
        permissionService.characterPermission(characterId);
            return characterRepositoryJdbcImpl.findCharacterById(characterId);

    }

    public List<Character> getCharacters(CharacterRequest characterRequest)  {
        return characterRepositoryJdbcImpl.findAllCharacters(characterRequest, getUserId());
    }

//    public Character setCharacterBody(int characterId, BodySkill bodySkill){
//
//
//    }


}
