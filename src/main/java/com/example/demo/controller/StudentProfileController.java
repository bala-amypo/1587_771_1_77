
// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/students")
// public class StudentProfileController {
// }
package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentProfile> save(@RequestBody StudentProfile profile) {
        return ResponseEntity.ok(service.createOrUpdateProfile(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProfileById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<StudentProfile> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> all() {
        return ResponseEntity.ok(service.getAllProfiles());
    }
}
