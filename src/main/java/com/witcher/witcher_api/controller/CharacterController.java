package com.witcher.witcher_api.controller;


import com.witcher.witcher_api.model.request.CharacterRequest;
import com.witcher.witcher_api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class CharacterController {

    private final ResponseEntity OK = ResponseEntity.status(HttpStatus.OK.value()).build();
    private final ResponseEntity ERROR = ResponseEntity.badRequest().build();
    private final ResponseEntity TEST = ResponseEntity.ok("Teszt!!!!");

    @Autowired
    CharacterService characterService;

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
    ResponseEntity<?> getCharacters(@ModelAttribute CharacterRequest characterRequest){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body( characterService.getCharacters(characterRequest));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PutMapping("character/{id}")
    ResponseEntity<?> setCharacter(@ModelAttribute CharacterRequest characterRequest){

        System.out.println();
        return OK;
    }
}

