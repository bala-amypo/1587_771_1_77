package com.example.demo.service.impl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.User;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.StudentProfileService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public StudentProfile createProfile(StudentProfile profile) {

        Long userId = profile.getUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found with id " + userId
                ));

        profile.setUser(user);

        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile getProfileById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "StudentProfile not found with id " + id
                ));
    }

    @Override
    public StudentProfile getProfileByEnrollmentId(String enrollmentId) {
        return studentProfileRepository.findByEnrollmentId(enrollmentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "StudentProfile not found with enrollmentId " + enrollmentId
                ));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return studentProfileRepository.findAll();
    }
}
