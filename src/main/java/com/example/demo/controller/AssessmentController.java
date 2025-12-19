package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentResultService service;

    public AssessmentController(AssessmentResultService service) {
        this.service = service;
    }

    // ✅ POST – Save assessment (updates DB)
    @PostMapping
    public AssessmentResult saveAssessment(@RequestBody AssessmentResult assessmentResult) {
        return service.saveAssessmentResult(assessmentResult);
    }

    // ✅ GET – Get all results by student
    @GetMapping("/student/{studentId}")
    public List<AssessmentResult> getByStudent(@PathVariable Long studentId) {
        return service.getResultsByStudentId(studentId);
    }

    // ✅ GET – Get result by student + skill
    @GetMapping("/student/{studentId}/skill/{skillId}")
    public List<AssessmentResult> getByStudentAndSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId) {

        return service.getResultsByStudentAndSkill(studentId, skillId);
    }
}
