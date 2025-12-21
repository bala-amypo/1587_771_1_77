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

    // =========================
    // CREATE STUDENT PROFILE
    // =========================
    @Override
    public StudentProfile createProfile(StudentProfile profile) {

        // Validate user input
        if (profile.getUser() == null || profile.getUser().getId() == null) {
            throw new RuntimeException("User ID must be provided");
        }

        Long userId = profile.getUser().getId();

        // Fetch existing user from DB
        User user = userRepo.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id " + userId));

        // Attach managed user entity
        profile.setUser(user);

        return studentRepo.save(profile);
    }

    // =========================
    // GET PROFILE BY ID
    // =========================
    @Override
    public StudentProfile getProfileById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found with id " + id));
    }

    // =========================
    // GET PROFILE BY ENROLLMENT ID
    // =========================
    @Override
    public StudentProfile getProfileByEnrollmentId(String enrollmentId) {
        return studentRepo.findByEnrollmentId(enrollmentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found with enrollmentId " + enrollmentId));
    }

    // =========================
    // GET ALL PROFILES
    // =========================
    @Override
    public List<StudentProfile> getAllProfiles() {
        return studentRepo.findAll();
    }
}
