package com.example.jwtsecurity.services;

import com.example.jwtsecurity.DTO.AuthResponse;
import com.example.jwtsecurity.DTO.LoginRequest;
import com.example.jwtsecurity.DTO.RegisterRequest;
import com.example.jwtsecurity.Repository.UserRepo;
import com.example.jwtsecurity.Util.JwtUtil;
import com.example.jwtsecurity.entity.UserAuth;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
    private final UserRepo repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    public AuthService(UserRepo repo,PasswordEncoder encoder,AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }
    public String register(RegisterRequest req) {
        if (repo.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        UserAuth user = new UserAuth();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword((req.getPassword()));
        user.setRole(req.getRole());
        repo.save(user);
        return "Registered successfully";
    }
    public AuthResponse login(LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        UserAuth user = (UserAuth) repo.findByUsername(req.getUsername()).orElseThrow();
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return new AuthResponse(token);
    }
}