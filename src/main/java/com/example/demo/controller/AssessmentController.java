package com.example.demo.controller;

import com.example.demo.dto.AssessmentResultDTO;
import com.example.demo.service.AssessmentResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assessments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentResultService assessmentService;

    @PostMapping
    public ResponseEntity<AssessmentResultDTO> createAssessment(@Valid @RequestBody AssessmentResultDTO dto) {
        AssessmentResultDTO created = assessmentService.createAssessment(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssessmentResultDTO> getAssessmentById(@PathVariable Long id) {
        AssessmentResultDTO assessment = assessmentService.getAssessmentById(id);
        return ResponseEntity.ok(assessment);
    }

    @GetMapping
    public ResponseEntity<List<AssessmentResultDTO>> getAllAssessments() {
        List<AssessmentResultDTO> assessments = assessmentService.getAllAssessments();
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssessmentResultDTO>> getAssessmentsByStudent(@PathVariable Long studentId) {
        List<AssessmentResultDTO> assessments = assessmentService.getAssessmentsByStudentId(studentId);
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/skill/{skillId}")
    public ResponseEntity<List<AssessmentResultDTO>> getAssessmentsBySkill(@PathVariable Long skillId) {
        List<AssessmentResultDTO> assessments = assessmentService.getAssessmentsBySkillId(skillId);
        return ResponseEntity.ok(assessments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssessmentResultDTO> updateAssessment(@PathVariable Long id, @Valid @RequestBody AssessmentResultDTO dto) {
        AssessmentResultDTO updated = assessmentService.updateAssessment(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssessment(@PathVariable Long id) {
        assessmentService.deleteAssessment(id);
        return ResponseEntity.noContent().build();
    }
}
