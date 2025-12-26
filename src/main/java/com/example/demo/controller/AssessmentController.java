package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentService; // Rename this import
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService; // Rename the field type

    @PostMapping
    public AssessmentResult record(@RequestBody AssessmentResult result) {
        return assessmentService.recordAssessment(result);
    }
}