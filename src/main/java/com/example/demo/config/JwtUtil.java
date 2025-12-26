package com.example.demo.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import com.example.demo.entity.User;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private final Key key;
    private final long validityMs;

    public JwtUtil(String secret, long validityMs) {
            this.key = Keys.hmacShaKeyFor(secret.getBytes());
            this.validityMs = validityMs;
    }

    public String generateToken(User user) {

        String role = user.getRole(); // role is STRING per spec

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("email", user.getEmail())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> validateAndParse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
