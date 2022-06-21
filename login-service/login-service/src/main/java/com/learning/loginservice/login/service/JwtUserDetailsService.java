package com.learning.loginservice.login.service;

import com.learning.loginservice.login.dto.JwtRequest;
import com.learning.loginservice.login.dto.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtUserDetailsService {
    @Value("${jwt.secret}")
    private String secretKey;
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String loadUserByUsername(JwtRequest authenticationRequest)  {
        if ("username".equals(authenticationRequest.getUsername())) {
           return getJWTToken(authenticationRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("User not found with username: " +
                    authenticationRequest.getUsername());
        }
    }

    private String getJWTToken(String username) {
        String token = Jwts
                .builder()
                .setId("self")
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256,
                        secretKey).compact();
        /*JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwttoken(token);
        jwtResponse.setUsername(username);*/
        return token;
    }
}
