package com.witcher.witcher_api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.witcher.witcher_api.model.pojo.User;
import com.witcher.witcher_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final ResponseEntity OK = ResponseEntity.status(HttpStatus.OK.value()).build();
    private final ResponseEntity ERROR = ResponseEntity.badRequest().build();
    private final ResponseEntity TEST = ResponseEntity.ok("Teszt!!!!");

    @Autowired
    UserService userService;

    @GetMapping("/details")
    public ResponseEntity<?> getUserDerails(){
        try {
            return ResponseEntity.status(HttpStatus.OK.value()).body(userService.getCurrentUser());
        }catch (Exception e){
            return ERROR;
        }
    }

    @PatchMapping("/username/{newUsername}")
    ResponseEntity<?>  updateUserDetails(@PathVariable String newUsername){
        try {
            userService.setUsername(newUsername);
            return OK;
        }catch (Exception e){
            return ERROR;
        }
    }

    @PatchMapping("/email/{newEmail}")
    ResponseEntity<?>  updateUserEmail(@PathVariable String newEmail){
        try {
            userService.setEmail(newEmail);
            return OK;
        }catch (Exception e){
            return ERROR;
        }
    }

}
