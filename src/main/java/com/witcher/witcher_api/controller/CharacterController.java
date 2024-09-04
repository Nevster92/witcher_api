package com.witcher.witcher_api.controller;


import com.witcher.witcher_api.model.pojo.*;
import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.repository.CharacterRepo;
import com.witcher.witcher_api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping()
public class CharacterController {

    private final ResponseEntity OK = ResponseEntity.status(HttpStatus.OK.value()).build();
    private final ResponseEntity ERROR = ResponseEntity.badRequest().build();
    private final ResponseEntity TEST = ResponseEntity.ok("Teszt!!!!");

    @Autowired
    CharacterService characterService;

    @CrossOrigin
    @DeleteMapping("character/delete/{characterId}")
    ResponseEntity<?> deleteCharacter( @PathVariable Long characterId){
        try {
            characterService.deleteCharacter(characterId);
            return OK;
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PutMapping("character/create")
    ResponseEntity<?> createNewCharacter( @RequestBody Character newCharacter){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.createNewCharacter(newCharacter));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @GetMapping("/character/{characterId}")
    ResponseEntity<?> getCharacterById(@PathVariable Long characterId){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body( characterService.getCharacterById(characterId));
        }catch (Exception e){
            return ERROR;
        }
    }
    @CrossOrigin
    @GetMapping("/characters")
    ResponseEntity<?> getCharacters(){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body( characterService.getCharacters());
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PutMapping("/character/{characterId}")
    ResponseEntity<?> updateCharacter(@PathVariable Long characterId, @RequestBody Character character){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.setCharacterAttribute(characterId, character));
        }catch (Exception e){
            return ERROR;
        }
    }



}
 
