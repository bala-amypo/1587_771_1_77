package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    // POST /api/students
    @PostMapping
    public StudentProfile createProfile(@RequestBody StudentProfile profile) {
        return service.createProfile(profile);
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public StudentProfile getProfileById(@PathVariable Long id) {
        return service.getProfileById(id);
    }

    // GET /api/students/enrollment/{id}
    @GetMapping("/enrollment/{id}")
    public StudentProfile getProfileByEnrollmentId(@PathVariable String id) {
        return service.getProfileByEnrollmentId(id);
    }

    // GET /api/students
    @GetMapping
    public List<StudentProfile> getAllProfiles() {
        return service.getAllProfiles();
    }
}
