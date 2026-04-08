package com.example.jwtsecurity.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component

public class jwtUtil {
    private String jwtSecretKey="mySecretKeyThatIsAtLeast32CharactersLongHAHHAHAAAAAAAAAAAA";

    public  String generateToken(String username) {
        return Jwts.builder()
        .setSubject(username)
//.setIssuedAt(Date.from())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+5000))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
    }
}
