package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.DTO.AuthResponse;
import com.example.jwtsecurity.DTO.LoginRequest;
import com.example.jwtsecurity.DTO.RegisterRequest;
import com.example.jwtsecurity.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
public class Controller {
    @Autowired
    private  AuthService service;
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        return service.register(req);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {
        return service.login(req);
    }
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String userApi() {
        return "API working";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminApi() {
        return "Admin API";
    }
}