package com.witcher.witcher_api.controller;


import com.witcher.witcher_api.model.pojo.BodySkill;
import com.witcher.witcher_api.model.pojo.Character;
import com.witcher.witcher_api.model.pojo.Weapon;
import com.witcher.witcher_api.service.CharacterService;
import com.witcher.witcher_api.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {
    private final ResponseEntity OK = ResponseEntity.status(HttpStatus.OK.value()).build();
    private final ResponseEntity ERROR = ResponseEntity.badRequest().build();
    private final ResponseEntity TEST = ResponseEntity.ok("Teszt!!!!");


    @Autowired
    ItemService itemService;


    @CrossOrigin
    @GetMapping("weapon/{id}")
    ResponseEntity<?> selectWepaons(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(itemService.getWeapon(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("character/{id}/weapons")
    ResponseEntity<?> selectAllWepaons(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(itemService.getAllWeaponsByCharacter(id));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PutMapping("character/{id}/weapon")
    ResponseEntity<?> createNewWeapon(@PathVariable int id, @RequestBody Weapon weapon){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(itemService.createNewWeapon(weapon, id));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @DeleteMapping("weapon/{id}")
    ResponseEntity<?> createNewWeapon(@PathVariable int id){
        try {
            itemService.deleteWeapon(id);
            return OK;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ERROR;
        }
    }

    @CrossOrigin
    @PostMapping("weapon/{id}")
    ResponseEntity<?> updateWeaponAttributes(@PathVariable int id, @RequestBody Weapon weapon){
        try {
            weapon.setId(id);
            return ResponseEntity.status(HttpStatus.OK.value()).body(itemService.setWeaponAttributes(weapon));
        }catch (Exception e){
            return ERROR;
        }
    }



}
