package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository assessmentRepo;
    private final SkillGapRecommendationRepository recommendationRepo;
    private final StudentProfileRepository studentRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(
            AssessmentResultRepository assessmentRepo,
            SkillGapRecommendationRepository recommendationRepo,
            StudentProfileRepository studentRepo,
            SkillRepository skillRepo
    ) {
        this.assessmentRepo = assessmentRepo;
        this.recommendationRepo = recommendationRepo;
        this.studentRepo = studentRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(
            Long studentId,
            Long skillId
    ) {
        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Skill skill = skillRepo.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skillId);

        double avgScore = results.isEmpty()
                ? 0
                : results.stream().mapToDouble(AssessmentResult::getScore).average().orElse(0);

        double gap = 100 - avgScore;

        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(student)
                .skill(skill)
                .gapScore(gap)
                .generatedAt(Instant.now())
                .generatedBy("SYSTEM")
                .build();

        return recommendationRepo.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {
        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Skill> skills = skillRepo.findByActiveTrue();
        List<SkillGapRecommendation> list = new ArrayList<>();

        for (Skill s : skills) {
            list.add(computeRecommendationForStudentSkill(studentId, s.getId()));
        }
        return list;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepo.findByStudentOrdered(studentId);
    }
}
