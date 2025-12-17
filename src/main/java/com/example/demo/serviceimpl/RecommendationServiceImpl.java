package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository assessmentResultRepository;
    private final SkillGapRecommendationRepository skillGapRecommendationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    public RecommendationServiceImpl(AssessmentResultRepository assessmentResultRepository,
                                     SkillGapRecommendationRepository skillGapRecommendationRepository,
                                     StudentProfileRepository studentProfileRepository,
                                     SkillRepository skillRepository) {
        this.assessmentResultRepository = assessmentResultRepository;
        this.skillGapRecommendationRepository = skillGapRecommendationRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public void generateRecommendations(Long studentProfileId) {
        StudentProfile profile = studentProfileRepository.findById(studentProfileId)
                .orElseThrow(() -> new IllegalArgumentException("Student profile not found"));

        skillGapRecommendationRepository.deleteByStudentProfileId(studentProfileId);

        List<Skill> activeSkills = skillRepository.findByActiveTrue();

        List<SkillGapRecommendation> recommendations = activeSkills.stream()
                .map(skill -> {
                    Double currentScore = assessmentResultRepository.findByStudentProfileId(studentProfileId).stream()
                            .filter(ar -> ar.getSkill().getId().equals(skill.getId()))
                            .map(AssessmentResult::getScoreObtained)
                            .max(Double::compareTo)
                            .orElse(0.0);

                    Double gap = skill.getMinCompetencyScore() - currentScore;
                    if (gap <= 0) return null;

                    SkillGapRecommendation rec = new SkillGapRecommendation();
                    rec.setStudentProfile(profile);
                    rec.setSkill(skill);
                    rec.setGapScore(gap);

                    if (gap > 20) {
                        rec.setPriority("HIGH");
                        rec.setRecommendedAction("Intensive remediation needed for " + skill.getSkillName());
                    } else if (gap > 10) {
                        rec.setPriority("MEDIUM");
                        rec.setRecommendedAction("Additional practice recommended for " + skill.getSkillName());
                    } else {
                        rec.setPriority("LOW");
                        rec.setRecommendedAction("Review " + skill.getSkillName() + " periodically");
                    }

                    rec.setGeneratedAt(Timestamp.from(Instant.now()));
                    rec.setGeneratedBy("SYSTEM");

                    return rec;
                })
                .filter(rec -> rec != null)
                .toList();

        skillGapRecommendationRepository.saveAll(recommendations);
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsByStudent(Long studentProfileId) {
        return skillGapRecommendationRepository.findByStudentProfileIdOrderByGeneratedAtDesc(studentProfileId);
    }
}
