package com.example.demo.service.impl;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private SkillGapRecordRepository gapRepository;

    @Autowired
    private SkillGapRecommendationRepository recommendationRepository;

    @Override
    public void generateRecommendations(Long studentId) {

        List<SkillGapRecord> gaps =
                gapRepository.findByStudentProfileId(studentId);

        for (SkillGapRecord gap : gaps) {

            SkillGapRecommendation recommendation =
                    new SkillGapRecommendation();

            recommendation.setStudentProfile(gap.getStudentProfile());
            recommendation.setSkill(gap.getSkill());
            recommendation.setGapScore(gap.getGapScore());
            recommendation.setRecommendedAction(
                    "Improve skill: " + gap.getSkill().getSkillName());
            recommendation.setPriority(
                    gap.getGapScore() > 20 ? "HIGH" : "MEDIUM");

            recommendationRepository.save(recommendation);
        }
    }
}
