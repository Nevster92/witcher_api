package com.witcher.witcher_api.service;

import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.repository.CharacterRepo;
import com.witcher.witcher_api.utils.CharacterMapper;
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

    @Autowired
    private CharacterMapper characterMapper;

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
    public Character setCharacterAttribute(Long characterId, Character newCharacter) {
        Character character = characterRepo.findById(characterId).get();
        characterMapper.updateCharacterFromDto(newCharacter, character);
        characterRepo.save(character);
        return character;
    }


}
