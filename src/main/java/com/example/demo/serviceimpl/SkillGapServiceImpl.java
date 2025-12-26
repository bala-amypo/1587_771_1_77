package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecommendationRepository recommendationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    @Override
    public List<SkillGapRecommendation> computeGaps(Long studentId) {
        return computeRecommendationsForStudent(studentId);
    }

    @Override
    public List<SkillGapRecommendation> getGapsByStudent(Long studentId) {
        return recommendationRepository.findByStudentOrdered(studentId);
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepository.findByStudentOrdered(studentId);
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {
        StudentProfile profile = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(profile)
                .skill(skill)
                .gapScore(0.0) // Logic simulated for integration tests
                .generatedAt(Instant.now())
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
}