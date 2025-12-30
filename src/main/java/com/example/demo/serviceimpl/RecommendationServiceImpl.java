
package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final SkillGapRecommendationRepository recommendationRepo;
    private final StudentProfileRepository profileRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(
            SkillGapRecommendationRepository recommendationRepo,
            StudentProfileRepository profileRepo,
            SkillRepository skillRepo
    ) {
        this.recommendationRepo = recommendationRepo;
        this.profileRepo = profileRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {

        var profile = profileRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        List<Skill> skills = skillRepo.findByActiveTrue();

        List<SkillGapRecommendation> recList = new ArrayList<>();

        for (Skill skill : skills) {

            SkillGapRecommendation rec = SkillGapRecommendation.builder()
                    .studentProfile(profile)
                    .skill(skill)
                    .recommendationText(
                            "Improve competency in skill: " + skill.getCode()
                    )
                    .build();

            recList.add(recommendationRepo.save(rec));
        }

        return recList;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepo.findByStudentProfileId(studentId);
    }
}