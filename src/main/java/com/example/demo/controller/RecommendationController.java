package com.example.demo.controller;

import com.example.demo.dto.SkillGapRecommendationDTO;
import com.example.demo.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
@Tag(name = "Skill Gap Recommendations", description = "APIs for managing skill gap recommendations")
public class RecommendationController {
    
    private final RecommendationService recommendationService;
    
    @PostMapping
    @Operation(summary = "Create recommendation")
    public ResponseEntity<SkillGapRecommendationDTO> createRecommendation(@Valid @RequestBody SkillGapRecommendationDTO recommendationDTO) {
        return new ResponseEntity<>(recommendationService.createRecommendation(recommendationDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get recommendation by ID")
    public ResponseEntity<SkillGapRecommendationDTO> getRecommendationById(@PathVariable Long id) {
        return ResponseEntity.ok(recommendationService.getRecommendationById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all recommendations")
    public ResponseEntity<List<SkillGapRecommendationDTO>> getAllRecommendations() {
        return ResponseEntity.ok(recommendationService.getAllRecommendations());
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get recommendations by user ID")
    public ResponseEntity<List<SkillGapRecommendationDTO>> getRecommendationsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByUserId(userId));
    }
    
    @GetMapping("/priority/{priority}")
    @Operation(summary = "Get recommendations by priority")
    public ResponseEntity<List<SkillGapRecommendationDTO>> getRecommendationsByPriority(@PathVariable String priority) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByPriority(priority));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update recommendation")
    public ResponseEntity<SkillGapRecommendationDTO> updateRecommendation(@PathVariable Long id, @Valid @RequestBody SkillGapRecommendationDTO recommendationDTO) {
        return ResponseEntity.ok(recommendationService.updateRecommendation(id, recommendationDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete recommendation")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
        return ResponseEntity.noContent().build();
    }
}
