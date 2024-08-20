package com.hdfc.notificationadminportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maker")
public class MakerController {
    
    @PostMapping("/createNudgeTemplate")
    ResponseEntity<String> createNudgeTemplate(){
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }

    @GetMapping("/fetchTemplate")
    ResponseEntity<String> fetchTemplates(){
        return ResponseEntity.status(HttpStatus.OK).body("DATA");
    }

    @PutMapping("/updateNudgeTemplate")
    ResponseEntity<String> updateNudgeTemplate(){
        return ResponseEntity.status(HttpStatus.OK).body("DATA Updated");
    }

    @DeleteMapping("/deleteNudgeTemplate")
    ResponseEntity<String> deleteNudgeTemplate(){
        return ResponseEntity.status(HttpStatus.OK).body("DATA Deleted");
    }

}
