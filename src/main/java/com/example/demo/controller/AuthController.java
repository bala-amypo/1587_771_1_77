package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {

        User u = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(req.getPassword())
                .role(req.getRole() != null
                        ? User.Role.valueOf(req.getRole())
                        : User.Role.STUDENT)
                .build();

        return userService.register(u);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {

        User u = userService.findByEmail(req.getEmail());
        if (u == null || !encoder.matches(req.getPassword(), u.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(u);
        return new AuthResponse(token);
    }
}
