package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;

import java.util.List;

public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentResultRepository repo;

    public AssessmentServiceImpl(AssessmentResultRepository repo) {
        this.repo = repo;
    }

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {

        Double score = result.getScoreObtained();
        Double max = result.getMaxScore() == null ? 100.0 : result.getMaxScore();

        if (score == null || score < 0 || score > max) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        result.setMaxScore(max);
        return repo.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        return repo.findByStudentProfileId(studentId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        return repo.findByStudentProfileIdAndSkillId(studentId, skillId);
    }
}
