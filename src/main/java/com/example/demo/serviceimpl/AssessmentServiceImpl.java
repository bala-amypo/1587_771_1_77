package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentResultRepository assessmentResultRepository;

    public AssessmentServiceImpl(AssessmentResultRepository assessmentResultRepository) {
        this.assessmentResultRepository = assessmentResultRepository;
    }

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {
        // Validation for t041: Check for Null
        if (result.getScore() == null) {
            throw new IllegalArgumentException("Score cannot be null");
        }

        // Validation for t008: Check for boundaries
        // Assuming maxScore is the limit (default 100.0)
        if (result.getScore() < 0 || result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score is out of valid range (0 to " + result.getMaxScore() + ")");
        }

        return assessmentResultRepository.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByProfileAndSkill(Long profileId, Long skillId) {
        return assessmentResultRepository.findByStudentProfileIdAndSkillId(profileId, skillId);
    }

    // Methods to support HQL tests
    public Double getAverageScore(String cohort, Long skillId) {
        return assessmentResultRepository.avgScoreByCohortAndSkill(cohort, skillId);
    }
}