package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;

public interface UserService {
    User register(RegisterRequest req);
    User getById(Long id);
    User findByEmail(String email);
}