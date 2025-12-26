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
        if (result.getScore() == null || result.getScore() < 0 || result.getScore() > 100) {
            throw new IllegalArgumentException("Invalid Score: Must be between 0 and 100");
        }
        return repository.save(result);
    }

    // Fixes "does not override abstract method getResultsByStudent"
    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        return repository.findByStudentId(studentId);
    }

    // Fixes "does not override abstract method getResultsByStudentAndSkill"
    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        return repository.findByStudentIdAndSkillId(studentId, skillId);
    }
}