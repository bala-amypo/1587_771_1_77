package com.example.demo.service.impl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.User;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentRepo;
    private final UserRepository userRepo;

    public StudentProfileServiceImpl(StudentProfileRepository studentRepo,
                                     UserRepository userRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public StudentProfile createProfile(StudentProfile profile) {

        Long userId = profile.getUser().getId();

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        profile.setUser(user);

        return studentRepo.save(profile);
    }

    @Override
    public StudentProfile getProfileById(Long id) {
        return studentRepo.findById(id).orElseThrow();
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return studentRepo.findAll();
    }
}
