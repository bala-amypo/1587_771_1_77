package com.example.demo.controller;
import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/students")
public class StudentProfileController {
    @Autowired
    private StudentProfileService service;
    @PostMapping
    public StudentProfile create(@RequestBody StudentProfile profile) {
        return service.createProfile(profile);
    }
    @GetMapping("/{id}")
    public StudentProfile getById(@PathVariable Long id) {
        return service.getProfileById(id);
    }
    @GetMapping
    public List<StudentProfile> getAll() {
        return service.getAllProfiles();
    }
}
