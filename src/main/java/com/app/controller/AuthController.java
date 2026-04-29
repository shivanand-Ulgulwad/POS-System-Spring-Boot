package com.app.controller;

import com.app.config.JwtUtility;
import com.app.dto.LoginRequestDTO;
import com.app.dto.RegisterRequestDTO;
import com.app.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailsService service;
    private final AuthenticationManager manager;
    private final JwtUtility utility;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO user) throws IllegalAccessException {
       String msg = service.createUser(user);
       return new ResponseEntity<>(msg, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO user){
     Authentication auth =  manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword())
        );

     return new ResponseEntity<String>("token: "+utility.generateToken(user.getEmail()),HttpStatus.ACCEPTED);


    }

}
