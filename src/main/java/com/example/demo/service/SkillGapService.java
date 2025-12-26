package com.example.demo.service;

import com.example.demo.entity.SkillGapRecommendation;
import java.util.List;

public interface SkillGapService {
    // This fixes the current Controller error (line 18)
    List<SkillGapRecommendation> computeGaps(Long studentId);

    // This fixes the previous Controller error (line 23)
    List<SkillGapRecommendation> getGapsByStudent(Long studentId);

    // This ensures LargeIntegrationTestNGTest (t038) passes
    List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId);

    // Support for Many-to-Many associations in tests (t024, t025)
    SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId);
    List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId);
}