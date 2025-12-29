
// package com.example.demo.controller;

// import com.example.demo.entity.AssessmentResult;
// import com.example.demo.service.AssessmentService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/assessments")
// public class AssessmentController {

//     private final AssessmentService service;

//     public AssessmentController(AssessmentService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public ResponseEntity<AssessmentResult> record(@RequestBody AssessmentResult result) {
//         return ResponseEntity.ok(service.recordAssessment(result));
//     }
// }



package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {
    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping("/")
    public ResponseEntity<?> recordResult(@RequestBody AssessmentResult result) {
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