package com.example.demo.serviceimpl;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository repository, PasswordEncoder encoder, com.example.demo.config.JwtUtil jwtUtil) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public User register(RegisterRequest req) {
        if (repository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = User.builder()
            .fullName(req.getFullName())
            .email(req.getEmail())
            .password(encoder.encode(req.getPassword()))
            .role(req.getRole() != null ? req.getRole() : User.Role.STUDENT)
            .build();
        return repository.save(user);
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }
}