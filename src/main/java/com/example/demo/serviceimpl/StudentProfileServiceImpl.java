package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.User;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.StudentProfileService;

import org.springframework.stereotype.Service;

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
    public StudentProfile create(StudentProfile profile) {

        if (profile.getUser() == null || profile.getUser().getId() == null) {
            throw new RuntimeException("User ID is required");
        }

        User user = userRepository.findById(profile.getUser().getId())
                .orElseThrow(() ->
                        new RuntimeException("User not found with id " + profile.getUser().getId()));

        profile.setUser(user);
        return studentProfileRepository.save(profile);
    }

    @Override
    public List<StudentProfile> getAll() {
        return studentProfileRepository.findAll();
    }

    @Override
    public StudentProfile getById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found with id " + id));
    }

    @Override
    public List<StudentProfile> getByUserId(Long userId) {
        return studentProfileRepository.findByUserId(userId);
    }
}
