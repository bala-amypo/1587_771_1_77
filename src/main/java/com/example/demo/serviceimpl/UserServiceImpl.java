package com.example.demo.serviceimpl;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * Constructor Injection: The parameter order here is critical for 
     * Mockito's @InjectMocks to work correctly in your test suite.
     */
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(RegisterRequest req) {
        // Strict requirement for t013: message must be "Email already exists"
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(req.getRole() != null ? req.getRole() : User.Role.STUDENT)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }
}