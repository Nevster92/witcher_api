package com.witcher.witcher_api.service;

import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.model.pojo.User;
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
    UserService userService;

    @Autowired
    private CharacterMapper characterMapper;


    @Transactional
    public Character getCharacterById(Long characterId) throws Exception {
        permissionService.characterPermission(characterId);
        try {
        Character character = characterRepo.findById(characterId).get();
        }catch (Exception e){
            System.out.println("EXEP");
            System.out.println(e.getMessage());
        }

            return characterRepo.findById(characterId).get();
    }

    public List<Character> getCharacters()  {
        String userId = permissionService.getUserId();
        return characterRepo.findByUserId(userId);
    }

    public Character createNewCharacter(Character newCharacter){
        try {
            String userId = userService.getUserId();
            newCharacter.setUser(new User(userId));
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
