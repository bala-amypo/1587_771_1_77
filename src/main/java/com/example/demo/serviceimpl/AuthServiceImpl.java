package com.example.demo.service.impl;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User register(User user) {
        return userRepository.save(user);
    }
    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
