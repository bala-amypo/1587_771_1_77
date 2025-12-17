package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@Tag(
    name = "Recommendations",
    description = "Generate and retrieve skill gap recommendations"
)
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/generate/{studentId}")
    public void generateRecommendations(@PathVariable Long studentId) {
        recommendationService.generateRecommendations(studentId);
    }

    @GetMapping("/student/{studentId}")
    public List<SkillGapRecommendation> getRecommendationsByStudent(
            @PathVariable Long studentId) {
        return recommendationService.getRecommendationsByStudent(studentId);
    }
}
