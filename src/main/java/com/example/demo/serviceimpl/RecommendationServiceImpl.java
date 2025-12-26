package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository assessmentRepository;
    private final SkillGapRecommendationRepository recommendationRepository;
    private final StudentProfileRepository studentRepository;
    private final SkillRepository skillRepository;

    public RecommendationServiceImpl(
            AssessmentResultRepository assessmentRepository,
            SkillGapRecommendationRepository recommendationRepository,
            StudentProfileRepository studentRepository,
            SkillRepository skillRepository) {

        this.assessmentRepository = assessmentRepository;
        this.recommendationRepository = recommendationRepository;
        this.studentRepository = studentRepository;
        this.skillRepository = skillRepository;
    }

    private String priorityFromGap(double gap) {
        if (gap >= 20) return "HIGH";
        if (gap >= 10) return "MEDIUM";
        return "LOW";
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {

        StudentProfile sp = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        List<AssessmentResult> results =
                assessmentRepository.findByStudentProfileIdAndSkillId(studentId, skillId);

        double current = results.stream()
                .mapToDouble(AssessmentResult::getScore)
                .max()
                .orElse(0.0);

        double target = skill.getMinCompetencyScore() == null ? 100.0 : skill.getMinCompetencyScore();

        double gap = target - current;

        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(sp)
                .skill(skill)
                .gapScore(gap)
                .priority(priorityFromGap(gap))
                .generatedBy("SYSTEM")
                .generatedAt(Instant.now())
                .recommendedAction("Practice " + skill.getName())
                .build();

        return recommendationRepository.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));

        List<SkillGapRecommendation> list = new ArrayList<>();

        for (Skill s : skillRepository.findByActiveTrue()) {
            list.add(computeRecommendationForStudentSkill(studentId, s.getId()));
        }

        return list;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepository.findByStudentOrdered(studentId);
    }
}
