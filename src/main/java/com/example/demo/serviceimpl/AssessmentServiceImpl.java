package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository repository;

    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {
        if (result.getScore() == null) {
            throw new IllegalArgumentException("Score cannot be null");
        }
        if (result.getMaxScore() == null) {
            result.setMaxScore(100.0);
        }
        if (result.getScore() < 0 || result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score must be between 0 and maxScore");
        }
        return repository.save(result);
    }

    // FIX: Implementation for getResultsByStudent
    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        return repository.findByStudentProfileId(studentId);
    }

    // FIX: Implementation for getResultsByStudentAndSkill
    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        return repository.findByStudentProfileIdAndSkillId(studentId, skillId);
    }
}