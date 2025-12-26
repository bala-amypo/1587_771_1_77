package com.example.demo.config;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String secretKey;
    private final long expirationMillis;

    // Constructor used by both Spring and your TestNG @BeforeClass
    public JwtUtil(@Value("${jwt.secret:01234567890123456789012345678901}") String secretKey, 
                   @Value("${jwt.expiration:3600000}") long expirationMillis) {
        this.secretKey = secretKey;
        this.expirationMillis = expirationMillis;
    }

    /**
     * Generates a token for a specific user with claims for ID, Email, and Role.
     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Validates the token and returns the Jws<Claims> object.
     * Throws an exception if the token is expired or tampered with.
     */
    public Jws<Claims> validateAndParse(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
    }

    /**
     * Helper to extract specific claims without full validation object exposure
     */
    public Claims extractAllClaims(String token) {
        return validateAndParse(token).getBody();
    }
}