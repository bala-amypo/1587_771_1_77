package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import java.time.Instant;
import java.util.*;

public class RecommendationServiceImpl implements RecommendationService {
    private final AssessmentResultRepository assessmentRepo;
    private final SkillGapRecommendationRepository recommendationRepo;
    private final StudentProfileRepository profileRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(AssessmentResultRepository ar, SkillGapRecommendationRepository sgr, 
                                   StudentProfileRepository spr, SkillRepository sr) {
        this.assessmentRepo = ar; this.recommendationRepo = sgr; 
        this.profileRepo = spr; this.skillRepo = sr;
    }

    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {
        StudentProfile profile = profileRepo.findById(studentId).orElseThrow();
        Skill skill = skillRepo.findById(skillId).orElseThrow();
        
        List<AssessmentResult> results = assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skillId);
        double latestScore = results.isEmpty() ? 0.0 : results.get(results.size() - 1).getScore();
        
        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(profile).skill(skill)
                .gapScore(100.0 - latestScore)
                .generatedAt(Instant.now())
                .generatedBy("SYSTEM").build();
        return recommendationRepo.save(rec);
    }

    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {
        List<Skill> activeSkills = skillRepo.findByActiveTrue();
        List<SkillGapRecommendation> results = new ArrayList<>();
        for (Skill s : activeSkills) {
            results.add(computeRecommendationForStudentSkill(studentId, s.getId()));
        }
        return results;
    }

    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepo.findByStudentOrdered(studentId);
    }
}