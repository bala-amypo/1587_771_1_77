package com.example.demo.controller;

import com.example.demo.dto.RecommendationDTO;
import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{studentId}")
    public List<RecommendationDTO> getRecommendations(@PathVariable Long studentId) {
        // ✅ FIXED METHOD NAME
        return recommendationService.getRecommendationsByStudent(studentId);
    }
}
