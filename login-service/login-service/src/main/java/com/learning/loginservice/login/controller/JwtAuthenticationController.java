package com.learning.loginservice.login.controller;

import com.learning.loginservice.login.dto.JwtRequest;
import com.learning.loginservice.login.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/login")
public class JwtAuthenticationController {
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) {;
        return ResponseEntity.ok(jwtUserDetailsService.
                loadUserByUsername(authenticationRequest));
    }

}
