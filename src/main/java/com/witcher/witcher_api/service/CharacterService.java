package com.witcher.witcher_api.service;

import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.repository.CharacterRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@NoArgsConstructor
public class CharacterService {

    @Autowired
    CharacterRepo characterRepo;

    @Autowired
    PermissionService permissionService;

    private String getUserId()  {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt token = (Jwt) authentication.getPrincipal();
            return  token.getClaims().get("sub").toString();
    }

    public Character getCharacterById(Long characterId) throws Exception {
        permissionService.characterPermission(characterId);
            return characterRepo.findById(characterId).get();
    }

    public List<Character> getCharacters()  {
        return characterRepo.findByUserId(getUserId());
    }




    public Character createNewCharacter(Character newCharacter){
        try {
            return characterRepo.save(newCharacter);
        }catch (Exception e ){
            throw e;
        }
    }

    @Transactional
    public void deleteCharacter(Long characterId) throws Exception {
        permissionService.characterPermission(characterId);
        try {
           characterRepo.deleteById(characterId);
        }catch (Exception e ){
            throw  e;
        }
    }



    @Transactional
    public Character setCharacter(Long characterId, Character newCharacter) {
        Character character = characterRepo.findById(characterId).get();
        String[] ignoredFields = {"User"};
        //TODO össze kell fésülni... a newCharacter-el. Keresni rá valami lib-et.
        BeanUtils.copyProperties(newCharacter, character, ignoredFields);
        characterRepo.save(character);
        return character;
    }

    public Character testService(Long characterId) throws Exception {

        return characterRepo.findById(characterId).get();
    }

}
