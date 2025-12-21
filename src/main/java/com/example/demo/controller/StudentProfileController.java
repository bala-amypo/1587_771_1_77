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

    // POST
    @PostMapping
    public StudentProfile createProfile(@RequestBody StudentProfile profile) {
        return service.createProfile(profile);
    }

    // GET all
    @GetMapping
    public List<StudentProfile> getAllProfiles() {
        return service.getAllProfiles();
    }

    // GET by profile id
    @GetMapping("/{id}")
    public StudentProfile getProfileById(@PathVariable Long id) {
        return service.getProfileById(id);
    }

    // GET by enrollment id
    @GetMapping("/enrollment/{id}")
    public StudentProfile getByEnrollment(@PathVariable String id) {
        return service.getProfileByEnrollmentId(id);
    }
}
