package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.User;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.StudentProfileService;

import org.springframework.stereotype.Service;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;

    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository,
                                     UserRepository userRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudentProfile create(StudentProfile profile) {

        User user = profile.getUser();

        if (user == null || user.getId() == null) {
            throw new RuntimeException("User ID is required");
        }

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() ->
                        new RuntimeException("User not found with id " + user.getId()));

        profile.setUser(existingUser);
        return studentProfileRepository.save(profile);
    }
}
