package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Students")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    // POST /api/students
    @PostMapping
    public StudentProfile createProfile(@RequestBody StudentProfile profile) {
        return studentProfileService.createProfile(profile);
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public StudentProfile getById(@PathVariable Long id) {
        return studentProfileService.getProfileById(id);
    }

    // GET /api/students/enrollment/{id}
    @GetMapping("/enrollment/{id}")
    public StudentProfile getByEnrollmentId(@PathVariable String id) {
        return studentProfileService.getProfileByEnrollmentId(id);
    }

    // GET /api/students
    @GetMapping
    public List<StudentProfile> getAll() {
        return studentProfileService.getAllProfiles();
    }
}
