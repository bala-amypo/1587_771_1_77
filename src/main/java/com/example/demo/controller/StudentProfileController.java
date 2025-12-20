package com.example.demo.controller;

import com.example.demo.dto.StudentProfileDTO;
import com.example.demo.service.StudentProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    @PostMapping
    public ResponseEntity<StudentProfileDTO> createStudentProfile(@Valid @RequestBody StudentProfileDTO dto) {
        StudentProfileDTO created = studentProfileService.createStudentProfile(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileDTO> getStudentProfileById(@PathVariable Long id) {
        StudentProfileDTO profile = studentProfileService.getStudentProfileById(id);
        return ResponseEntity.ok(profile);
    }

    @GetMapping
    public ResponseEntity<List<StudentProfileDTO>> getAllStudentProfiles() {
        List<StudentProfileDTO> profiles = studentProfileService.getAllStudentProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<StudentProfileDTO> getStudentProfileByUserId(@PathVariable Long userId) {
        StudentProfileDTO profile = studentProfileService.getStudentProfileByUserId(userId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfileDTO> updateStudentProfile(@PathVariable Long id, @Valid @RequestBody StudentProfileDTO dto) {
        StudentProfileDTO updated = studentProfileService.updateStudentProfile(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentProfile(@PathVariable Long id) {
        studentProfileService.deleteStudentProfile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/dashboard")
    public ResponseEntity<?> getStudentDashboard(@PathVariable Long id) {
        Object dashboard = studentProfileService.getStudentDashboard(id);
        return ResponseEntity.ok(dashboard);
    }
}