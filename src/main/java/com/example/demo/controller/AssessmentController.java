package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentResultService service;

    @PostMapping
    public AssessmentResult add(@RequestBody AssessmentResult result) {
        return service.saveResult(result);
    }

    @GetMapping("/student/{studentId}")
    public List<AssessmentResult> getByStudent(
            @PathVariable Long studentId) {
        return service.getResultsByStudent(studentId);
    }
}
