package com.witcher.witcher_api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CharacterController {



    @CrossOrigin
    @GetMapping("/test")
    String test(){

        return "SIKER";
    }
}

