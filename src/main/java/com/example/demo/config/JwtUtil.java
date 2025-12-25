package com.example.demo.config;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private final Key signingKey;
    private final long expiryMillis;

    /**
     * Constructor expected by TestNG setup:
     * new JwtUtil("32+charsecret...", 3600000)
     */
    public JwtUtil(String secretKey, long expiryMillis) {
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expiryMillis = expiryMillis;
    }

    /**
     * Generates token with claims required by tests:
     * userId, email, role
     */
    public String generateToken(User user) {

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole() != null ? user.getRole().name() : null)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expiryMillis))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Used in tests:
     * var parsed = jwtUtil.validateAndParse(token)
     *
     * Must throw exception when:
     * - token is malformed
     * - signature is tampered
     * - token expired
     */
    public Jws<Claims> validateAndParse(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token);
    }
}
