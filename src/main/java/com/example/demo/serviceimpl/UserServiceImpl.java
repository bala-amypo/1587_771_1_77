// package com.example.demo.serviceimpl;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class UserServiceImpl implements UserService {


//     private final UserRepository repo;
//     private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//     public UserServiceImpl(UserRepository repo) {
//         this.repo = repo;
//     }

//     public User register(User user) {
//         if (repo.existsByEmail(user.getEmail())) {
//             throw new IllegalArgumentException("Email already exists");
//         }

//         user.setPassword(encoder.encode(user.getPassword()));

//         if (user.getRole() == null) {
//             user.setRole(User.Role.STUDENT);
//         }

//         return repo.save(user);
//     }

//     public User getById(Long id) {
//         return repo.findById(id)
//                 .orElseThrow(() -> new RuntimeException("user not found"));
//     }

//     public User findByEmail(String email) {
//         return repo.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("user not found"));
//     }

//     public List<User> listInstructors() {
//         return repo.findByRole(User.Role.INSTRUCTOR);
//     }
// }




