package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email,
                      @RequestParam String password) {
        return authService.login(email, password);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return authService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }
}
