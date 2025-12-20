package com.example.demo.serviceimpl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    
    @Override
    @Transactional
    public UserDTO register(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new DuplicateResourceException("Username already exists: " + userDTO.getUsername());
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + userDTO.getEmail());
        }
        
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }
    
    @Override
    public UserDTO login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));
        
        if (!user.getPassword().equals(password)) {
            throw new ResourceNotFoundException("Invalid username or password");
        }
        
        return mapToDTO(user);
    }
    
    @Override
    public void logout(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }
    
    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                null,
                user.getEmail(),
                user.getRole()
        );
    }
}