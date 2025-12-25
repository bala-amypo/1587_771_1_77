package com.example.demo.serviceImpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;

import java.util.List;

public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository repo;

    public AssessmentResultServiceImpl(AssessmentResultRepository repo) {
        this.repo = repo;
    }

    @Override
    public AssessmentResult recordResult(AssessmentResult result) {

        if (result.getScore() == null || result.getMaxScore() == null) {
            throw new IllegalArgumentException("Score or maxScore cannot be null");
        }

        if (result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score cannot exceed maxScore");
        }

        return repo.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentProfileId) {
        return repo.findRecentByStudent(studentProfileId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentProfileId, Long skillId) {
        return repo.findByStudentProfileIdAndSkillId(studentProfileId, skillId);
    }
}
