
package com.example.demo.config;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private final Key key;
    private final long validity;

    public JwtUtil(String secret, long validity) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validity = validity;
    }

    public String generateToken(User u) {
        return Jwts.builder()
                .claim("userId", u.getId())
                .claim("email", u.getEmail())
                .claim("role", u.getRole().name())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> validateAndParse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
