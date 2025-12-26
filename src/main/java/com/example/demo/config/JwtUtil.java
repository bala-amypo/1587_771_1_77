package com.example.demo.config;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.Date;
import javax.crypto.SecretKey;

// @Component
// public class JwtUtil {
//     private final SecretKey key;
//     private final long expiration;

//     public JwtUtil(String secret, long expiration) {
//         this.key = Keys.hmacShaKeyFor(secret.getBytes());
//         this.expiration = expiration;
//     }

//     public String generateToken(User user) {
//         return Jwts.builder()
//                 .claim("userId", user.getId())
//                 .claim("email", user.getEmail())
//                 .claim("role", user.getRole().name())
//                 .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                 .signWith(key, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     // Required: Returns Jws<Claims> so the test can call .getBody()
//     public Jws<Claims> validateAndParse(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token);
//     }
// }
@Component
public class JwtUtil {

    private final SecretKey key;
    private final long expiration;

    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
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
