package com.example.demo.config;

import com.example.demo.entity.Auth;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "demo-secret-key";

    private final long EXPIRY = 1000 * 60 * 60; // 1 hour

    public String generateToken(Auth auth) {

        return Jwts.builder()
                .setSubject(auth.getEmail())
                .claim("role", auth.getRole().name())
                .claim("userId", auth.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String extractEmail(String token) {
        return parse(token).getBody().getSubject();
    }

    public boolean isExpired(String token) {
        return parse(token).getBody().getExpiration().before(new Date());
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token);
    }
}
