
package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {
    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @PostMapping("/")
    public ResponseEntity<StudentProfile> create(@RequestBody StudentProfile profile) {
        return ResponseEntity.ok(studentProfileService.createOrUpdateProfile(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentProfileService.getProfileById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<StudentProfile> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(studentProfileService.getByUserId(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentProfile>> getAll() {
        return ResponseEntity.ok(studentProfileService.getAllProfiles());
    }
}