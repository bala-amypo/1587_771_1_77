package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Profiles", description = "Operations related to student profiles")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @PostMapping
    public StudentProfile createProfile(@RequestBody StudentProfile profile) {
        return studentProfileService.createProfile(profile);
    }

    @GetMapping("/{id}")
    public StudentProfile getProfileById(@PathVariable Long id) {
        return studentProfileService.getProfileById(id);
    }

    @GetMapping("/enrollment/{enrollmentId}")
    public StudentProfile getProfileByEnrollmentId(@PathVariable String enrollmentId) {
        return studentProfileService.getProfileByEnrollmentId(enrollmentId);
    }

    @GetMapping
    public List<StudentProfile> getAllProfiles() {
        return studentProfileService.getAllProfiles();
    }
}
