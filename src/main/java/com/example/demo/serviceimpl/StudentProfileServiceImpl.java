package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {
    private final StudentProfileRepository studentProfileRepository;

    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public StudentProfile createOrUpdateProfile(StudentProfile profile) {
        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile getProfileById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student profile not found in database")); // ResourceNotFoundException logic 
    }

    @Override
    public StudentProfile getByUserId(Long userId) {
        return studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("profile not found")); 
    }

    @Override
    public StudentProfile getProfileByEnrollmentId(String enrollmentId) {
        return studentProfileRepository.findByEnrollmentId(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return studentProfileRepository.findAll();
    }
}