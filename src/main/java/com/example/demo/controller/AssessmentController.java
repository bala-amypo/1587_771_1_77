package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
@Tag(name = "Assessments")
public class AssessmentController {

    private final AssessmentResultService service;

    public AssessmentController(AssessmentResultService service) {
        this.service = service;
    }

    @PostMapping
    public AssessmentResult recordResult(@RequestBody AssessmentResult result) {
        return service.recordResult(result);
    }

    @GetMapping("/student/{studentId}")
    public List<AssessmentResult> getResultsByStudent(@PathVariable Long studentId) {
        return service.getResultsByStudent(studentId);
    }

    @GetMapping("/student/{studentId}/skill/{skillId}")
    public AssessmentResult getResultByStudentAndSkill(@PathVariable Long studentId, @PathVariable Long skillId) {
        return service.getResultsByStudentAndSkill(studentId, skillId);
    }
}