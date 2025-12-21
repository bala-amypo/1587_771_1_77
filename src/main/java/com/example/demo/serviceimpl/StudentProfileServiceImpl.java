package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository profileRepository;
    private final UserRepository userRepository;

    public StudentProfileServiceImpl(StudentProfileRepository profileRepository,
                                     UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudentProfile createProfile(StudentProfile profile) {

        Long userId = profile.getUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        profile.setUser(user);
        profile.setActive(true);

        return profileRepository.save(profile);
    }

    @Override
    public StudentProfile getProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("StudentProfile not found"));
    }

    @Override
    public StudentProfile getProfileByEnrollmentId(String enrollmentId) {
        return profileRepository.findByEnrollmentId(enrollmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("StudentProfile not found"));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return profileRepository.findAll();
    }
}
