package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody StudentProfile profile) {
        return ResponseEntity.ok(service.createOrUpdateProfile(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProfileById(id));
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public ResponseEntity<?> getByEnrollment(@PathVariable String enrollmentId) {
        return ResponseEntity.ok(service.getProfileByEnrollmentId(enrollmentId));
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentProfile>> getAll() {
        return ResponseEntity.ok(service.getAllProfiles());
    }
}
