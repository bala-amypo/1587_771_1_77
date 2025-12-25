package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gaps")
public class SkillGapController {

    private final RecommendationService service;

    public SkillGapController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping("/compute/{studentId}")
    public List<SkillGapRecommendation> compute(@PathVariable Long studentId) {
        return service.computeRecommendationsForStudent(studentId);
    }

    @GetMapping("/student/{studentId}")
    public List<SkillGapRecommendation> get(@PathVariable Long studentId) {
        return service.getRecommendationsForStudent(studentId);
    }
}
