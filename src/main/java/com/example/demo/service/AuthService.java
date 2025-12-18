package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface AuthService {

    User register(User user);

    User login(String email, String password);

    User getUserById(Long id);

    List<User> getAllUsers();
}
