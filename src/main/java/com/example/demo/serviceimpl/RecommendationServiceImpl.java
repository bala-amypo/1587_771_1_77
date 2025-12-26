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
        // Required for t024: Check existence of profile and skill
        StudentProfile profile = profileRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("profile not found")); [cite: 1, 58]
        Skill skill = skillRepo.findById(skillId).orElseThrow(() -> new ResourceNotFoundException("skill not found")); [cite: 1, 58]
        
        List<AssessmentResult> results = assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skillId);
        double currentScore = results.isEmpty() ? 0.0 : results.get(results.size() - 1).getScore();
        double gap = (skill.getMinCompetencyScore() != null ? skill.getMinCompetencyScore() : 80.0) - currentScore;

        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(profile)
                .skill(skill)
                .gapScore(gap)
                .generatedAt(Instant.now())
                .generatedBy("SYSTEM")
                .build();
        return recommendationRepo.save(rec); [cite: 1, 58, 59]
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {
        // Required for t025: Fetch all active skills and compute recommendations
        return skillRepo.findByActiveTrue().stream()
                .map(s -> computeRecommendationForStudentSkill(studentId, s.getId()))
                .collect(Collectors.toList()); [cite: 1, 60, 64]
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        // Required for t038
        return recommendationRepo.findByStudentOrdered(studentId); [cite: 1, 86]
    }
}