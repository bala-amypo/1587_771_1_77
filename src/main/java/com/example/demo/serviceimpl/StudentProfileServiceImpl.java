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

    private final StudentProfileRepository profileRepo;
    private final UserRepository userRepo;

    public StudentProfileServiceImpl(StudentProfileRepository profileRepo,
                                     UserRepository userRepo) {
        this.profileRepo = profileRepo;
        this.userRepo = userRepo;
    }

    @Override
    public StudentProfile createProfile(StudentProfile profile) {

        Long userId = profile.getUser().getId();

        User user = userRepo.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id " + userId));

        profile.setUser(user);
        return profileRepo.save(profile);
    }

    @Override
    public StudentProfile getProfileById(Long id) {
        return profileRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("StudentProfile not found with id " + id));
    }

    @Override
    public StudentProfile getProfileByEnrollmentId(String enrollmentId) {
        return profileRepo.findByEnrollmentId(enrollmentId)
                .orElseThrow(() ->
                        new RuntimeException("StudentProfile not found"));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return profileRepo.findAll();
    }
}
