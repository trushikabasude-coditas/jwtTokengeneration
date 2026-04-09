package com.example.jwtsecurity.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

import static io.jsonwebtoken.Jwts.*;

@Component

public class JwtUtil {
    private static final String SECRET = "secrethgebfmsanopfwj,dsmfnAl";
    private static final SecretKey key= Keys.hmacShaKeyFor(SECRET.getBytes());
    public static String generateToken(String username, String role) {
        return builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }
    public boolean validate(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
