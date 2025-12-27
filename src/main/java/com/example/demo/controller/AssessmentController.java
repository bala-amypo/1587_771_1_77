
// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/assessments")
// public class AssessmentController {
// }


package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentResultService assessmentService;

    public AssessmentController(AssessmentResultService assessmentService) {
        this.assessmentService = assessmentService;
    }

    // POST / – Record assessment result
    @PostMapping("/")
    public ResponseEntity<AssessmentResult> recordAssessment(@RequestBody AssessmentResult result) {
        return new ResponseEntity<>(assessmentService.recordAssessment(result), HttpStatus.CREATED);
    }

    // GET /student/{studentId} – Get all results for student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssessmentResult>> getResultsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(assessmentService.getResultsByStudent(studentId));
    }

    // GET /student/{studentId}/skill/{skillId} – Get results for student and specific skill
    @GetMapping("/student/{studentId}/skill/{skillId}")
    public ResponseEntity<List<AssessmentResult>> getResultsByStudentAndSkill(
            @PathVariable Long studentId, 
            @PathVariable Long skillId) {
        return ResponseEntity.ok(assessmentService.getResultsByStudentAndSkill(studentId, skillId));
    }
}