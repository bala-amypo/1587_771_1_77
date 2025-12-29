
// package com.example.demo.config;

// import com.example.demo.entity.User;
// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import java.security.Key;
// import java.util.Date;

// public class JwtUtil {

//     private final Key key;
//     private final long validity;

//     public JwtUtil(String secret, long validity) {
//         this.key = Keys.hmacShaKeyFor(secret.getBytes());
//         this.validity = validity;
//     }

//     public String generateToken(User u) {
//         return Jwts.builder()
//                 .claim("userId", u.getId())
//                 .claim("email", u.getEmail())
//                 .claim("role", u.getRole().name())
//                 .setExpiration(new Date(System.currentTimeMillis() + validity))
//                 .signWith(key)
//                 .compact();
//     }

//     public Jws<Claims> validateAndParse(String token) {
//         return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//     }
// }




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

    // 32+ bytes strong key (VALID)
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
