package com.example.demo.service;

import com.example.demo.entity.SkillGapRecommendation;
import java.util.List;

public interface SkillGapService {
    // Controller at line 23 is looking for this:
    List<SkillGapRecommendation> getGapsByStudent(Long studentId);

    // Required by Test t038 in LargeIntegrationTestNGTest
    List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId);

    // Required by Topic 6 (Many-to-Many associations)
    SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId);
    List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId);
}