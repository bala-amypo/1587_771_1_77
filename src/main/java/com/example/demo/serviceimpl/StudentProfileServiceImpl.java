package com.example.demo.service.impl;

import com.example.demo.entity.StudentProfile;
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
    public StudentProfile saveProfile(StudentProfile profile) {
        return repository.save(profile);
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return repository.findAll();
    }

    @Override
    public List<StudentProfile> getProfilesByUserId(Long userId) {
        return repository.findByUser_Id(userId);
    }
}
