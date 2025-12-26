package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    User register(RegisterRequest req);
    User register(User user); // Required for t003 
    User getById(Long id);    // Required for t055 
    User findByEmail(String email); // Required for t013 
    List<User> listInstructors();   // Required for t042, t056 
}