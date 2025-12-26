package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final AssessmentResultRepository assessmentRepo;
    private final SkillGapRecommendationRepository recommendationRepo;
    private final StudentProfileRepository profileRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(AssessmentResultRepository ar, SkillGapRecommendationRepository sgr, 
                                     StudentProfileRepository spr, SkillRepository sr) {
        this.assessmentRepo = ar;
        this.recommendationRepo = sgr;
        this.profileRepo = spr;
        this.skillRepo = sr;
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {
        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new ResourceNotFoundException("skill not found"));
        StudentProfile profile = profileRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("profile not found"));
        
        List<AssessmentResult> results = assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skillId);
        double currentScore = results.isEmpty() ? 0.0 : results.get(results.size() - 1).getScore();
        double minReq = skill.getMinCompetencyScore() != null ? skill.getMinCompetencyScore() : 0.0;
        double gap = minReq - currentScore;

        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(profile)
                .skill(skill)
                .gapScore(gap)
                .generatedAt(Instant.now())
                .generatedBy("SYSTEM")
                .build();
        return recommendationRepo.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {
        return skillRepo.findByActiveTrue().stream()
                .map(s -> computeRecommendationForStudentSkill(studentId, s.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepo.findByStudentOrdered(studentId);
    }
}