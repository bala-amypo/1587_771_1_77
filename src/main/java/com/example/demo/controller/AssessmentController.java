package com.example.demo.controller;

import com.example.demo.dto.AssessmentResultDTO;
import com.example.demo.service.AssessmentResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessment-results")
@RequiredArgsConstructor
@Tag(name = "Assessment Results", description = "APIs for managing assessment results")
public class AssessmentResultController {
    
    private final AssessmentResultService assessmentService;
    
    @PostMapping
    @Operation(summary = "Create assessment result")
    public ResponseEntity<AssessmentResultDTO> createAssessmentResult(@Valid @RequestBody AssessmentResultDTO resultDTO) {
        return new ResponseEntity<>(assessmentService.createAssessmentResult(resultDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get assessment result by ID")
    public ResponseEntity<AssessmentResultDTO> getAssessmentResultById(@PathVariable Long id) {
        return ResponseEntity.ok(assessmentService.getAssessmentResultById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all assessment results")
    public ResponseEntity<List<AssessmentResultDTO>> getAllAssessmentResults() {
        return ResponseEntity.ok(assessmentService.getAllAssessmentResults());
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get assessment results by user ID")
    public ResponseEntity<List<AssessmentResultDTO>> getAssessmentResultsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(assessmentService.getAssessmentResultsByUserId(userId));
    }
    
    @GetMapping("/skill/{skillId}")
    @Operation(summary = "Get assessment results by skill ID")
    public ResponseEntity<List<AssessmentResultDTO>> getAssessmentResultsBySkillId(@PathVariable Long skillId) {
        return ResponseEntity.ok(assessmentService.getAssessmentResultsBySkillId(skillId));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update assessment result")
    public ResponseEntity<AssessmentResultDTO> updateAssessmentResult(@PathVariable Long id, @Valid @RequestBody AssessmentResultDTO resultDTO) {
        return ResponseEntity.ok(assessmentService.updateAssessmentResult(id, resultDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete assessment result")
    public ResponseEntity<Void> deleteAssessmentResult(@PathVariable Long id) {
        assessmentService.deleteAssessmentResult(id);
        return ResponseEntity.noContent().build();
    }
}