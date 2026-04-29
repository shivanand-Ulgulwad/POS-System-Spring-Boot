package com.app.controller;

import com.app.entity.AppUser;
import com.app.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private CustomUserDetailsService service;

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("message from test", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
       List<AppUser> users = service.loadUsers();
       return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
