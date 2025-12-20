package com.example.demo.service;

import com.example.demo.dto.UserDTO;

public interface AuthService {
    UserDTO register(UserDTO userDTO);
    UserDTO login(String username, String password);
    void logout(Long userId);
}