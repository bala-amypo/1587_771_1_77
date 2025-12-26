package com.example.demo.service;

import com.example.demo.entity.SkillGapRecommendation;
import java.util.List;

public interface SkillGapService {
    // This matches the method name Maven is complaining about
    List<SkillGapRecommendation> getGapsByStudent(Long studentId);
    
    // Logic for Topic 6: Many-to-Many simulation
    SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId);
    
    List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId);
}