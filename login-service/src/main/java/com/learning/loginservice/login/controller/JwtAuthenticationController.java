package com.learning.loginservice.login.controller;

import com.learning.loginservice.login.dto.JwtRequest;
import com.learning.loginservice.login.service.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/login")
@Slf4j
public class JwtAuthenticationController {
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) {;
        log.info("Controller Class ::: Authenticate service ");
        return ResponseEntity.ok(jwtUserDetailsService.
                loadUserByUsername(authenticationRequest));
    }

}
