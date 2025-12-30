package com.example.demo.config;

import com.example.demo.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Key KEY =
            Keys.hmacShaKeyFor("MY_SUPER_SECRET_KEY_1234567890123456".getBytes());

    public String generateToken(User user) {

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getKey() {
        return KEY;
    }
}
