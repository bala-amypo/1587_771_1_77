// src/main/java/com/example/demo/serviceimpl/RecommendationServiceImpl.java
package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository assessmentResultRepository;
    private final SkillGapRecommendationRepository recommendationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {
        StudentProfile profile = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        
        // FIX: Ensure 'skillId' is used correctly here
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        List<AssessmentResult> results = assessmentResultRepository.findByStudentProfileIdAndSkillId(studentId, skillId);
        
        double avgScore = results.stream().mapToDouble(AssessmentResult::getScore).average().orElse(0.0);
        
        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(profile)
                .skill(skill)
                .gapScore(100.0 - avgScore)
                .generatedBy("SYSTEM")
                .build();
                
        return recommendationRepository.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {
        List<Skill> activeSkills = skillRepository.findByActiveTrue();
        return activeSkills.stream()
                .map(s -> computeRecommendationForStudentSkill(studentId, s.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepository.findByStudentOrdered(studentId);
    }
}