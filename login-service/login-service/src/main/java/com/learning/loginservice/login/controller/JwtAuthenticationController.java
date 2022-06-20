package com.learning.loginservice.login.controller;

import com.learning.loginservice.login.dto.JwtRequest;
import com.learning.loginservice.login.dto.JwtResponse;
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
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) {
        String token = jwtUserDetailsService.getJWTToken(authenticationRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/validateToken")
    public ResponseEntity<?> validate(@RequestParam String token) {
        return ResponseEntity.ok(jwtUserDetailsService.getValidateToken(token));
    }
}
