package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.entity.Skill;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository assessmentRepo;
    private final SkillRepository skillRepo;
    private final SkillGapRecommendationRepository recommendationRepo;

    public RecommendationServiceImpl(
            AssessmentResultRepository assessmentRepo,
            SkillRepository skillRepo,
            SkillGapRecommendationRepository recommendationRepo
    ) {
        this.assessmentRepo = assessmentRepo;
        this.skillRepo = skillRepo;
        this.recommendationRepo = recommendationRepo;
    }

    @Override
    public List<SkillGapRecommendation> generateRecommendations(Long studentId) {

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileId(studentId);

        return results.stream()
                .filter(r -> r.getScoreObtained() < 60) // ✅ FIXED
                .map(result -> {

                    Skill skill = skillRepo.findById(result.getSkill().getId())
                            .orElseThrow(() -> new RuntimeException("Skill not found"));

                    SkillGapRecommendation rec = SkillGapRecommendation.builder()
                            .studentProfileId(studentId)
                            .skillId(skill.getId())
                            .skillName(skill.getSkillName()) // ✅ FIXED (was getName())
                            .recommendationText("Improve performance through focused practice")
                            .build();

                    return recommendationRepo.save(rec);
                })
                .toList();
    }
}
