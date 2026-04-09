package com.example.jwtsecurity.services;

import com.example.jwtsecurity.Repository.UserRepo;
import com.example.jwtsecurity.entity.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userDetailService implements UserDetailsService {
    @Autowired
    private  UserRepo repo;

    public userDetailService(UserRepo repo) {
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserAuth user = repo.findByUsername(username).orElseThrow();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}