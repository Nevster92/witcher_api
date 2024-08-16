package com.witcher.witcher_api.controller;


import com.witcher.witcher_api.model.pojo.*;
import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.model.request.CharacterRequest;
import com.witcher.witcher_api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @DeleteMapping("character/delete/{id}")
    ResponseEntity<?> deleteCharacter( @PathVariable int id){
        try {
            characterService.deleteCharacter(id);
            return OK;
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PutMapping("character/create")
    ResponseEntity<?> createNewCharacter( @RequestBody String characterName){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.createNewCharacter(characterName));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @GetMapping("/character/{id}")
    ResponseEntity<?> getCharacterById(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body( characterService.getCharacterById(id));
        }catch (Exception e){

            return ERROR;
        }
    }
    @CrossOrigin
    @GetMapping("characters")
    ResponseEntity<?> getCharacters(){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body( characterService.getCharacters());
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PostMapping("character/{id}/bodySkills")
    ResponseEntity<?> updateCharacterBodySkills(@PathVariable int id, @RequestBody BodySkill bodySkill){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.setCharacterBody(id, bodySkill));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PostMapping("character/{id}/intelligenceSkills")
    ResponseEntity<?> updateCharacterIntelligenceSkills(@PathVariable int id, @RequestBody IntelligenceSkill intelligenceSkill){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.setCharacterIntelligence(id, intelligenceSkill));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PostMapping("character/{id}/dexteritySkills")
    ResponseEntity<?> updateCharacterDexteritySkills(@PathVariable int id, @RequestBody DexteritySkill dexteritySkill){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.setCharacterDexterity(id, dexteritySkill));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PostMapping("character/{id}/empathySkills")
    ResponseEntity<?> updateCharacterEmpathySkills(@PathVariable int id, @RequestBody EmpathySkill empathySkill){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.setCharacterEmpathy(id, empathySkill));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PostMapping("character/{id}/willSkills")
    ResponseEntity<?> updateCharacterWillSkills(@PathVariable int id, @RequestBody WillSkill willSkill){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.setCharacterWill(id, willSkill));
        }catch (Exception e){
            return ERROR;
        }
    }
    @CrossOrigin
    @PostMapping("character/{id}/craftSkills")
    ResponseEntity<?> updateCharacterCraftSkills(@PathVariable int id, @RequestBody CraftSkill craftSkill){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.setCharacterCraft(id, craftSkill));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PostMapping("character/{id}/reflexSkills")
    ResponseEntity<?> updateCharacterReflexSkills(@PathVariable int id, @RequestBody ReflexSkill reflexSkill){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.setCharacterReflex(id, reflexSkill));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PostMapping("character/{id}")
    ResponseEntity<?> updateCharacterCore(@PathVariable int id, @RequestBody Character character){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.setCharacterCore(id, character));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ERROR;
        }
    }

    @CrossOrigin
    @PatchMapping("character/{characterId}/r_arm")
    ResponseEntity<?> updateRightArm(@PathVariable int characterId, @RequestBody Map<String, Object> body){
        int weaponId = (Integer) body.get("weapon_id");
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(characterService.setRigthArm(characterId, weaponId));
        }catch (Exception e){
            return ERROR;
        }
    }


}
 
