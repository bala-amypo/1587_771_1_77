package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {
    private final AssessmentResultService assessmentService;

    public AssessmentController(AssessmentResultService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping("/")
    public ResponseEntity<?> record(@RequestBody AssessmentResult result) {
        return ResponseEntity.ok(assessmentService.recordAssessment(result));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(assessmentService.getResultsByStudent(studentId));
    }

    @GetMapping("/student/{studentId}/skill/{skillId}")
    public ResponseEntity<?> getByStudentAndSkill(@PathVariable Long studentId, @PathVariable Long skillId) {
        return ResponseEntity.ok(assessmentService.getResultsByStudentAndSkill(studentId, skillId));
    }
}