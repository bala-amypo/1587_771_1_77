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

    // ✅ POST /api/students
    @PostMapping
    public StudentProfile create(@RequestBody StudentProfile profile) {
        return service.create(profile);
    }

    // ✅ GET /api/students
    @GetMapping
    public List<StudentProfile> getAll() {
        return service.getAll();
    }

    // ✅ GET /api/students/{id}
    @GetMapping("/{id}")
    public StudentProfile getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // ✅ GET /api/students/user/{userId}
    @GetMapping("/user/{userId}")
    public StudentProfile getByUser(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }
}
