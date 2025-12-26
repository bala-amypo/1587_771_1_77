package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {
    private final StudentProfileService profileService;

    public StudentProfileController(StudentProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createProfile(@RequestBody StudentProfile profile) {
        return ResponseEntity.ok(profileService.createOrUpdateProfile(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public ResponseEntity<?> getByEnrollment(@PathVariable String enrollmentId) {
        return ResponseEntity.ok(profileService.getProfileByEnrollmentId(enrollmentId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }
}