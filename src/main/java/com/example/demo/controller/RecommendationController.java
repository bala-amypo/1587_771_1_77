
// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/recommendations")
// public class RecommendationController {
// }




package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/generate/{studentId}")
    public ResponseEntity<List<SkillGapRecommendation>> generate(@PathVariable Long studentId) {
        return ResponseEntity.ok(recommendationService.computeRecommendationsForStudent(studentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SkillGapRecommendation>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsForStudent(studentId));
    }
}