package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repository;

    public StudentProfileServiceImpl(StudentProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentProfile createOrUpdateProfile(StudentProfile profile) {

        if (profile.getEnrollmentId() != null &&
            repository.existsByEnrollmentId(profile.getEnrollmentId()) &&
            (profile.getId() == null)) {

            throw new IllegalArgumentException("EnrollmentId must be unique");
        }

        return repository.save(profile);
    }

    @Override
    public StudentProfile getProfileById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student profile not found"));
    }

    @Override
    public StudentProfile getByUserId(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student profile not found"));
    }

    @Override
    public StudentProfile getProfileByEnrollmentId(String enrollmentId) {
            return repository.findByEnrollmentId(enrollmentId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Student profile not found"));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return repository.findAll();
    }
}
