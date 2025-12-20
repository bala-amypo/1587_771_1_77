package com.example.demo.controller;

import com.example.demo.dto.AssessmentRequest;
import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentResultService service;

    public AssessmentController(AssessmentResultService service) {
        this.service = service;
    }

    @PostMapping
    public AssessmentResult save(@RequestBody AssessmentRequest request) {
        return service.saveAssessment(request);
    }
}
