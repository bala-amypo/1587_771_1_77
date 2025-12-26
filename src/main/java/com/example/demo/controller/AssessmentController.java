package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentResultService assessmentResultService;

    @Autowired
    public AssessmentController(AssessmentResultService assessmentResultService) {
        this.assessmentResultService = assessmentResultService;
    }

    @PostMapping
    public ResponseEntity<AssessmentResult> submitAssessment(@RequestBody AssessmentResult result) {
        return ResponseEntity.ok(assessmentResultService.saveResult(result));
    }
}