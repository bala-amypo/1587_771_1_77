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

        if (profile.getUser() == null) {
            throw new RuntimeException("User details are required");
        }

        User userInput = profile.getUser();
        User user;

        // ✅ CASE 1: User ID provided → fetch
        if (userInput.getId() != null) {
            user = userRepo.findById(userInput.getId())
                    .orElseThrow(() ->
                            new RuntimeException("User not found with id " + userInput.getId()));
        }
        // ✅ CASE 2: Email provided → fetch or create
        else if (userInput.getEmail() != null) {
            user = userRepo.findByEmail(userInput.getEmail())
                    .orElseGet(() -> {
                        User newUser = new User();
                        newUser.setFullName(userInput.getFullName());
                        newUser.setEmail(userInput.getEmail());
                        newUser.setPassword(userInput.getPassword());
                        newUser.setRole(userInput.getRole());
                        return userRepo.save(newUser);
                    });
        }
        else {
            throw new RuntimeException("User ID or Email must be provided");
        }

        profile.setUser(user);
        return studentRepo.save(profile);
    }

    @Override
    public StudentProfile getProfileById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found with id " + id));
    }

    @Override
    public StudentProfile getProfileByEnrollmentId(String enrollmentId) {
        return studentRepo.findByEnrollmentId(enrollmentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with enrollmentId " + enrollmentId));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return studentRepo.findAll();
    }
}
