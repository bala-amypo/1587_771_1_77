
package com.example.demo.controller;

import com.example.demo.service.RecommendationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // Generate recommendations for a student
    @PostMapping("/generate/{studentId}")
    public ResponseEntity<?> generate(@PathVariable Long studentId) {
        return ResponseEntity.ok(
                recommendationService.computeRecommendationsForStudent(studentId)
        );
    }

    // Fetch stored recommendations (ordered by generatedAt)
    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(
                recommendationService.getRecommendationsForStudent(studentId)
        );
    }
}
