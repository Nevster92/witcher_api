package com.witcher.witcher_api.controller;


import com.witcher.witcher_api.model.pojo.Armor;
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
    ResponseEntity<?> getWeapon(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(itemService.getWeapon(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("character/{id}/weapons")
    ResponseEntity<?> getAllWepaons(@PathVariable int id){
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
    ResponseEntity<?> deleteWeapon(@PathVariable int id){
        try {
            itemService.deleteWeapon(id);
            return OK;
        }catch (Exception e){
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


    //##############################################################
    //############################ ARMOR ###########################
    //##############################################################


    @CrossOrigin
    @GetMapping("armor/{id}")
    ResponseEntity<?> getArmor(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(itemService.getArmor(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("character/{characterId}/armors")
    ResponseEntity<?> getAllArmors(@PathVariable int characterId){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(itemService.getAllArmorsByCharacter(characterId));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PutMapping("character/{characterId}/armor")
    ResponseEntity<?> createNewArmor(@PathVariable int characterId, @RequestBody Armor armor){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(itemService.createNewArmor(armor, characterId));
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @DeleteMapping("armor/{armorId}")
    ResponseEntity<?> deleteArmor(@PathVariable int armorId){
        try {
            itemService.deleteArmor(armorId);
            return OK;
        }catch (Exception e){
            return ERROR;
        }
    }

    @CrossOrigin
    @PostMapping("armor/{armorId}")
    ResponseEntity<?> updateArmorAttributes(@PathVariable int id, @RequestBody Armor armor){
        try {
            armor.setId(id);
            return ResponseEntity.status(HttpStatus.OK.value()).body(itemService.setArmorAttributes(armor));
        }catch (Exception e){
            return ERROR;
        }
    }



}
