package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository assessmentRepository;
    private final SkillGapRecommendationRepository recommendationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    public RecommendationServiceImpl(AssessmentResultRepository assessmentRepository,
                                     SkillGapRecommendationRepository recommendationRepository,
                                     StudentProfileRepository studentProfileRepository,
                                     SkillRepository skillRepository) {
        this.assessmentRepository = assessmentRepository;
        this.recommendationRepository = recommendationRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public void generateRecommendations(Long studentProfileId) {
        StudentProfile profile = studentProfileRepository.findById(studentProfileId)
                .orElseThrow(() -> new IllegalArgumentException("Student profile not found"));

        recommendationRepository.deleteByStudentProfileId(studentProfileId);

        List<Skill> activeSkills = skillRepository.findByActiveTrue();
        List<SkillGapRecommendation> recommendations = new ArrayList<>();

        for (Skill skill : activeSkills) {
            Double currentScore = assessmentRepository.findByStudentProfileId(studentProfileId).stream()
                    .filter(ar -> ar.getSkill().getId().equals(skill.getId()))
                    .map(AssessmentResult::getScoreObtained)
                    .max(Double::compareTo)
                    .orElse(0.0);

            Double gap = skill.getMinCompetencyScore() - currentScore;
            if (gap <= 0) continue;

            SkillGapRecommendation rec = new SkillGapRecommendation();
            rec.setStudentProfile(profile);
            rec.setSkill(skill);
            rec.setGapScore(gap);
            rec.setGeneratedBy("SYSTEM");

            if (gap > 20) {
                rec.setPriority("HIGH");
                rec.setRecommendedAction("Intensive practice required on " + skill.getSkillName());
            } else if (gap > 10) {
                rec.setPriority("MEDIUM");
                rec.setRecommendedAction("Additional practice recommended on " + skill.getSkillName());
            } else {
                rec.setPriority("LOW");
                rec.setRecommendedAction("Review " + skill.getSkillName() + " as needed");
            }

            recommendations.add(rec);
        }

        recommendationRepository.saveAll(recommendations);
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsByStudent(Long studentProfileId) {
        return recommendationRepository.findByStudentProfileIdOrderByGeneratedAtDesc(studentProfileId);
    }
}