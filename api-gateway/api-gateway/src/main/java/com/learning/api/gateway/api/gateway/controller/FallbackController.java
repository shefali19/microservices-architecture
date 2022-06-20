package com.learning.api.gateway.api.gateway.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/userServiceFallBack")
    @CircuitBreaker(name = "USER-SERVICE")
    public String userServiceFallBack() {
        return "User Service is taking longer than expected to respond." +
                " Please check back in sometime ";
    }

    @GetMapping("/departmentServiceFallBack")
    @CircuitBreaker(name = "DEPARTMENT-SERVICE")
    public String departmentServiceFallBack() {
        return "Department Service is taking longer than expected to respond." +
                " Please check back in sometime.";
    }

    @GetMapping("/loginServiceFallBack")
    @CircuitBreaker(name = "LOGIN-SERVICE")
    public String loginServiceFallBack() {
        return "Login Service is taking longer than expected to respond." +
                " Please check back in sometime ";
    }

    @GetMapping("/uploadServiceFallBack")
    @CircuitBreaker(name = "UPLOAD-SERVICE")
    public String uploadServiceFallBack() {
        return "Upload Service is taking longer than expected to respond." +
                " Please check back in sometime ";
    }

    @GetMapping("/downloadServiceFallBack")
    @CircuitBreaker(name = "DOWNLOAD-SERVICE")
    public String downloadServiceFallBack() {
        return "Download Service is taking longer than expected to respond." +
                " Please check back in sometime ";
    }
}
