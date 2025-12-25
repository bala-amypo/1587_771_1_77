package com.example.demo.serviceImpl;

import com.example.demo.entity.Auth;
import com.example.demo.repository.AuthRepository;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class AuthServiceImpl implements AuthService {

    private final AuthRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(AuthRepository repo) {
        this.repo = repo;
    }

    @Override
    public Auth register(Auth a) {

        if (repo.existsByEmail(a.getEmail()))
            throw new IllegalArgumentException("Email already exists");

        a.setPassword(encoder.encode(a.getPassword()));

        return repo.save(a);
    }

    @Override
    public Auth getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public Auth findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public List<Auth> listInstructors() {
            return repo.findByRole(Auth.Role.INSTRUCTOR);
    }
}
