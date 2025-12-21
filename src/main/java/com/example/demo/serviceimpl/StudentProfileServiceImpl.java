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

    private final StudentProfileRepository studentRepo;
    private final UserRepository userRepo;

    public StudentProfileServiceImpl(StudentProfileRepository studentRepo,
                                     UserRepository userRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public StudentProfile create(StudentProfile profile) {

        User incomingUser = profile.getUser();
        User savedUser;

        // ✅ FIX: NO lambda using mutable variable
        if (incomingUser.getId() != null) {
            savedUser = userRepo.findById(incomingUser.getId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "User not found with id " + incomingUser.getId()));
        } else {
            savedUser = userRepo.save(incomingUser);
        }

        profile.setUser(savedUser);
        return studentRepo.save(profile);
    }

    @Override
    public List<StudentProfile> getAll() {
        return studentRepo.findAll();
    }

    @Override
    public StudentProfile getById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id " + id));
    }
}
