
package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository profileRepository;

    public StudentProfileServiceImpl(StudentProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public StudentProfile createOrUpdateProfile(StudentProfile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public StudentProfile getProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @Override
    public StudentProfile getByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user"));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return profileRepository.findAll();
    }
}