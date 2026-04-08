package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.service.Tokenservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
    private Tokenservice service;
    @PostMapping("/username")
    public String usernameToken(@RequestBody String username) {
        return service.usernameToken(username);
    }

}
