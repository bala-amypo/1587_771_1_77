

package com.example.demo.serviceimpl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register new user
    @Override
    public User register(User user) {

        userRepository.findByUsername(user.getUsername())
                .ifPresent(u -> { throw new RuntimeException("Username already exists"); });

        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> { throw new RuntimeException("Email already registered"); });

        user.setActive(true);
        return userRepository.save(user);
    }

    // Login user
    @Override
    public User login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    // Get user by id
    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Get all users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Soft delete / deactivate
    @Override
    public void deactivateUser(Long id) {
        User user = getById(id);
        user.setActive(false);
        userRepository.save(user);
    }
}