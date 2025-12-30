
package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {

    User register(User user);

    User login(String username, String password);

    User getById(Long id);

    List<User> getAllUsers();

    void deactivateUser(Long id);
}