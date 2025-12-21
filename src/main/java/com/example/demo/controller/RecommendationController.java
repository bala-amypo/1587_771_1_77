package com.example.demo.controller;

import com.example.demo.dto.RecommendationDTO;
import com.example.demo.service.RecommendationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // POST /api/recommendations/generate/{studentId}
    @PostMapping("/generate/{studentId}")
    public List<RecommendationDTO> generate(@PathVariable Long studentId) {
        return recommendationService.generateRecommendations(studentId);
    }

    // GET /api/recommendations/student/{studentId}
    @GetMapping("/student/{studentId}")
    public List<RecommendationDTO> getRecommendations(@PathVariable Long studentId) {
        return recommendationService.getRecommendationsByStudent(studentId);
    }
}
