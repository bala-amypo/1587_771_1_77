package com.example.demo.serviceimpl;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final SkillGapRecommendationRepository repository;

    public RecommendationServiceImpl(SkillGapRecommendationRepository repository) {
        this.repository = repository;
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {
        SkillGapRecommendation rec = new SkillGapRecommendation();
        rec.setStudentId(studentId);
        rec.setSkillId(skillId);
        rec.setPriority("MEDIUM");
        rec.setRecommendation("Improve this skill through practice");

        return repository.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {
        // For now return stored recommendations
        return repository.findByStudentId(studentId);
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return repository.findByStudentId(studentId);
    }
}
