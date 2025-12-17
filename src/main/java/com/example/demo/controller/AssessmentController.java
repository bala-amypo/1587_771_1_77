package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
@Tag(
    name = "Assessments",
    description = "Operations for recording and retrieving assessment results"
)
public class AssessmentController {

    private final AssessmentResultService assessmentResultService;

    public AssessmentController(AssessmentResultService assessmentResultService) {
        this.assessmentResultService = assessmentResultService;
    }

    @PostMapping
    public AssessmentResult recordResult(@RequestBody AssessmentResult result) {
        return assessmentResultService.recordResult(result);
    }

    @GetMapping("/student/{studentId}")
    public List<AssessmentResult> getResultsByStudent(@PathVariable Long studentId) {
        return assessmentResultService.getResultsByStudent(studentId);
    }

    @GetMapping("/student/{studentId}/skill/{skillId}")
    public AssessmentResult getResultByStudentAndSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId) {
        return assessmentResultService.getResultsByStudentAndSkill(studentId, skillId);
    }
}
