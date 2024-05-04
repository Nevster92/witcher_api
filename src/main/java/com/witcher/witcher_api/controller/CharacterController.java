package com.witcher.witcher_api.controller;


import com.witcher.witcher_api.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/character")
public class CharacterController {

    private final ResponseEntity OK = ResponseEntity.status(HttpStatus.OK.value()).build();
    private final ResponseEntity ERROR = ResponseEntity.badRequest().build();
    private final ResponseEntity TEST = ResponseEntity.ok("Teszt!!!!");

    @Autowired
    CharacterService characterService;

    @CrossOrigin
    @GetMapping("/{id}")
    ResponseEntity<?> getCharacterById(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body( characterService.getCharacterById(id));
        }catch (Exception e){
            return ERROR;
        }
    }
}

