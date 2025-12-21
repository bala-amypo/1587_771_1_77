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
    public StudentProfile create(@RequestBody StudentProfile profile) {
        return service.create(profile);
    }

    // GET ALL
    @GetMapping
    public List<StudentProfile> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public StudentProfile getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET BY USER ID
    @GetMapping("/user/{userId}")
    public StudentProfile getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }
}
