package com.example.demo.serviceimpl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {

        // ❌ REMOVED: user.getRole().isBlank()
        // ✅ CORRECT ENUM CHECK
        if (user.getRole() == null) {
            user.setRole(User.Role.STUDENT); // default role
        }

        return userRepository.save(user);
    }
}
