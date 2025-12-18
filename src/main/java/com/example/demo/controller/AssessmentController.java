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
    @PostMapping("/save")
    public AssessmentResult saveAssessment(@RequestBody AssessmentResult assessmentResult) {
        return service.saveAssessmentResult(assessmentResult); // <-- fixed method name
    }
    @GetMapping("/student/{id}")
    public List<AssessmentResult> getByStudent(@PathVariable Long id) {
        return service.getResultsByStudentId(id); // <-- fixed method name
    }
}
