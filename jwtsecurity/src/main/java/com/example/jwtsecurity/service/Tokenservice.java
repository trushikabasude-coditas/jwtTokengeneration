package com.example.jwtsecurity.service;

import com.example.jwtsecurity.util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Tokenservice {
    @Autowired
    private jwtUtil jwtutil;

    public String usernameToken(String username) {
String token=jwtutil.generateToken(username);
        System.out.println(token);
        return token;
    }
}
