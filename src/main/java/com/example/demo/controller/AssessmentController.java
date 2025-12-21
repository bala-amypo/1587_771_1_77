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

    private final AssessmentResultService assessmentService;

    public AssessmentController(AssessmentResultService assessmentService) {
        this.assessmentService = assessmentService;
    }

    // POST /api/assessments
    @PostMapping
    public AssessmentResult recordResult(@RequestBody AssessmentResult result) {
        return assessmentService.recordResult(result);
    }

    // GET /api/assessments/student/{studentId}
    @GetMapping("/student/{studentId}")
    public List<AssessmentResult> getByStudent(@PathVariable Long studentId) {
        return assessmentService.getResultsByStudent(studentId);
    }

    // GET /api/assessments/student/{studentId}/skill/{skillId}
    @GetMapping("/student/{studentId}/skill/{skillId}")
    public List<AssessmentResult> getByStudentAndSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId) {
        return assessmentService.getResultsByStudentAndSkill(studentId, skillId);
    }
}
