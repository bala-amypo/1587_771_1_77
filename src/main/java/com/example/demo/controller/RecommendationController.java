package com.example.demo.controller;

import com.example.demo.dto.SkillGapRecommendationDTO;
import com.example.demo.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping
    public ResponseEntity<SkillGapRecommendationDTO> createRecommendation(@Valid @RequestBody SkillGapRecommendationDTO dto) {
        SkillGapRecommendationDTO created = recommendationService.createRecommendation(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillGapRecommendationDTO> getRecommendationById(@PathVariable Long id) {
        SkillGapRecommendationDTO recommendation = recommendationService.getRecommendationById(id);
        return ResponseEntity.ok(recommendation);
    }

    @GetMapping
    public ResponseEntity<List<SkillGapRecommendationDTO>> getAllRecommendations() {
        List<SkillGapRecommendationDTO> recommendations = recommendationService.getAllRecommendations();
        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SkillGapRecommendationDTO>> getRecommendationsByStudent(@PathVariable Long studentId) {
        List<SkillGapRecommendationDTO> recommendations = recommendationService.getRecommendationsByStudentId(studentId);
        return ResponseEntity.ok(recommendations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillGapRecommendationDTO> updateRecommendation(@PathVariable Long id, @Valid @RequestBody SkillGapRecommendationDTO dto) {
        SkillGapRecommendationDTO updated = recommendationService.updateRecommendation(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/generate/{studentId}")
    public ResponseEntity<List<SkillGapRecommendationDTO>> generateRecommendations(@PathVariable Long studentId) {
        List<SkillGapRecommendationDTO> recommendations = recommendationService.generateRecommendationsForStudent(studentId);
        return ResponseEntity.ok(recommendations);
    }
}