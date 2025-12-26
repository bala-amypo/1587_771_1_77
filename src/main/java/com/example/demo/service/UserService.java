package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    // Required for Controller
    User register(RegisterRequest req); 
    
    // Required for t003
    User register(User user); 
    
    // Required for t055
    User getById(Long id); 
    
    // Required for t013
    User findByEmail(String email); 
    
    // Required for t042 and t056
    List<User> listInstructors(); 
}