package com.example.demo.service;

import com.example.demo.dto.UserDTO;

public interface AuthService {
    UserDTO register(UserDTO userDTO);
    String login(String username, String password);
    UserDTO getUserById(Long userId);
    boolean validateToken(String token);
    String refreshToken(String token);
}