package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDTO;
import com.example.demo.service.StudentProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-profiles")
@RequiredArgsConstructor
@Tag(name = "Student Profile Management", description = "APIs for managing student profiles")
public class StudentProfileController {
    
    private final StudentProfileService profileService;
    
    @PostMapping
    @Operation(summary = "Create student profile")
    public ResponseEntity<StudentProfileDTO> createProfile(@Valid @RequestBody StudentProfileDTO profileDTO) {
        return new ResponseEntity<>(profileService.createProfile(profileDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get profile by ID")
    public ResponseEntity<StudentProfileDTO> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get profile by user ID")
    public ResponseEntity<StudentProfileDTO> getProfileByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(profileService.getProfileByUserId(userId));
    }
    
    @GetMapping
    @Operation(summary = "Get all profiles")
    public ResponseEntity<List<StudentProfileDTO>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update profile")
    public ResponseEntity<StudentProfileDTO> updateProfile(@PathVariable Long id, @Valid @RequestBody StudentProfileDTO profileDTO) {
        return ResponseEntity.ok(profileService.updateProfile(id, profileDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete profile")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/department/{department}")
    @Operation(summary = "Get profiles by department")
    public ResponseEntity<List<StudentProfileDTO>> getProfilesByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(profileService.getProfilesByDepartment(department));
    }
}