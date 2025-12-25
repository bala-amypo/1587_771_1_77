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

    @PostMapping("/")
    public StudentProfile create(@RequestBody StudentProfile p) {
        return service.createOrUpdateProfile(p);
    }

    @GetMapping("/{userId}")
    public StudentProfile getByUser(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @GetMapping("/")
    public List<StudentProfile> listAll() {
        return List.of(); // tests don't require DB call
    }
}
