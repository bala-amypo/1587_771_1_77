package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profiles")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    public StudentProfile createProfile(@RequestBody StudentProfile profile) {
        return service.createProfile(profile);
    }

    @GetMapping("/{id}")
    public StudentProfile getProfileById(@PathVariable Long id) {
        return service.getProfileById(id);
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public StudentProfile getProfileByEnrollmentId(@PathVariable String enrollmentId) {
        return service.getProfileByEnrollmentId(enrollmentId);
    }

    @GetMapping
    public List<StudentProfile> getAllProfiles() {
        return service.getAllProfiles();
    }
}