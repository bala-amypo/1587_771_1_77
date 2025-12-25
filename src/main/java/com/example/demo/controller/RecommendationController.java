package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping("/generate/{studentId}")
    public List<SkillGapRecommendation> generate(@PathVariable Long studentId) {
        return service.computeRecommendationsForStudent(studentId);
    }

    @GetMapping("/student/{studentId}")
    public List<SkillGapRecommendation> list(@PathVariable Long studentId) {
        return service.getRecommendationsForStudent(studentId);
    }
}
