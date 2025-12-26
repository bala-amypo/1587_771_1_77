// package com.example.demo.serviceimpl;

// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.entity.User;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class UserServiceImpl implements UserService {
//     private final UserRepository userRepository;
//     private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//     public UserServiceImpl(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public User register(RegisterRequest req) {
//         if (userRepository.existsByEmail(req.getEmail())) {
//             throw new IllegalArgumentException("Email already exists");
//         }
//         User user = User.builder()
//                 .fullName(req.getFullName())
//                 .email(req.getEmail())
//                 .password(passwordEncoder.encode(req.getPassword()))
//                 .role(req.getRole() != null ? req.getRole() : User.Role.STUDENT)
//                 .build();
//         return userRepository.save(user);
//     }

//     @Override
//     public User register(User user) {
//         if (userRepository.existsByEmail(user.getEmail())) {
//             throw new IllegalArgumentException("Email already exists");
//         }
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         return userRepository.save(user);
//     }

//     @Override
//     public User getById(Long id) {
//         return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
//     }

//     @Override
//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
//     }

//     @Override
//     public List<User> listInstructors() {
//         return userRepository.findByRole(User.Role.INSTRUCTOR);
//     }
// }
package com.example.demo.serviceimpl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository repository) { this.repository = repository; }

    public User register(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> listInstructors() {
        return repository.findByRole(User.Role.INSTRUCTOR);
    }
}
