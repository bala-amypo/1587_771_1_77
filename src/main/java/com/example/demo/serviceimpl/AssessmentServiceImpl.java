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
        // Validation required by tests t008 and t041
        if (result.getScore() == null || result.getScore() < 0 || result.getScore() > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        return repository.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        return repository.findByStudentProfileId(studentId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        // This method resolves the compilation error
        return repository.findByStudentProfileIdAndSkillId(studentId, skillId);
    }
}