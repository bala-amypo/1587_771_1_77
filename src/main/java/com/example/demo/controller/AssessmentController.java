
// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/assessments")
// public class AssessmentController {
// }
package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    private final AssessmentService service;

    public AssessmentController(AssessmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AssessmentResult> record(@RequestBody AssessmentResult result) {
        return ResponseEntity.ok(service.recordAssessment(result));
    }
}
