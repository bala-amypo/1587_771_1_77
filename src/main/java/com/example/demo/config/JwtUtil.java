// package com.example.demo.config;

// import com.example.demo.entity.User;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jws;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import java.nio.charset.StandardCharsets;
// import java.util.Date;
// import javax.crypto.SecretKey;

// @Component
// public class JwtUtil {
//     private final SecretKey key;
//     private final long expirationMs;

//     public JwtUtil(String secret, long expirationMs) {
//         this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//         this.expirationMs = expirationMs;
//     }

//     public String generateToken(User user) {
//         return Jwts.builder()
//                 .setSubject(user.getEmail())
//                 .claim("userId", user.getId())
//                 .claim("email", user.getEmail())
//                 .claim("role", user.getRole().name())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
//                 .signWith(key, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     public Jws<Claims> validateAndParse(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token);
//     }
// }

package com.example.demo.config;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key;
    private final long expirationMs;

    public JwtUtil(String secret, long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
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