package com.example.demo.service;

import com.example.demo.entity.SkillGapRecommendation;
import java.util.List;

public interface RecommendationService {
    void generateRecommendations(Long studentProfileId);
    List<SkillGapRecommendation> getRecommendationsByStudent(Long studentProfileId);
}