
package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.StudentProfileRepository;

import java.util.ArrayList;
import java.util.List;

public class RecommendationServiceImpl {

    private final AssessmentResultRepository arRepo;
    private final SkillGapRecommendationRepository recRepo;
    private final StudentProfileRepository spRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(
            AssessmentResultRepository arRepo,
            SkillGapRecommendationRepository recRepo,
            StudentProfileRepository spRepo,
            SkillRepository skillRepo) {

        this.arRepo = arRepo;
        this.recRepo = recRepo;
        this.spRepo = spRepo;
        this.skillRepo = skillRepo;
    }

    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {

        StudentProfile sp = spRepo.findById(studentId).orElseThrow();
        Skill skill = skillRepo.findById(skillId).orElseThrow();

        SkillGapRecommendation recommendation = SkillGapRecommendation.builder()
                .studentProfile(sp)
                .skill(skill)
                .gapScore(50.0)
                .priority("MEDIUM")
                .recommendedAction("Practice")
                .generatedBy("SYSTEM")
                .build();

        return recRepo.save(recommendation);
    }

    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {

        spRepo.findById(studentId).orElseThrow();

        List<Skill> skills = skillRepo.findByActiveTrue();
        List<SkillGapRecommendation> result = new ArrayList<>();

        for (Skill skill : skills) {
            result.add(computeRecommendationForStudentSkill(studentId, skill.getId()));
        }

        return result;
    }

    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recRepo.findByStudentOrdered(studentId);
    }
}
