package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    // ✅ POST – save student profile
    @PostMapping
    public StudentProfile createProfile(@RequestBody StudentProfile profile) {
        return service.saveProfile(profile);
    }

    // ✅ GET – all profiles
    @GetMapping
    public List<StudentProfile> getAllProfiles() {
        return service.getAllProfiles();
    }

    // ✅ GET – profiles by userId
    @GetMapping("/user/{userId}")
    public List<StudentProfile> getByUserId(@PathVariable Long userId) {
        return service.getProfilesByUserId(userId);
    }
}
