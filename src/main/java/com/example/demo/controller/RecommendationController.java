package com.example.demo.controller;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;
import com.example.demo.repository.SkillGapRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
    @Autowired
    private RecommendationService service;
    @Autowired
    private SkillGapRecommendationRepository repository;
    @PostMapping("/generate/{studentId}")
    public String generate(@PathVariable Long studentId) {
        service.generateRecommendations(studentId);
        return "Recommendations generated successfully";
    }
    @GetMapping("/student/{studentId}")
    public List<SkillGapRecommendation> getRecommendations(
            @PathVariable Long studentId) {
        return repository.findByStudentProfileId(studentId);
    }
}
