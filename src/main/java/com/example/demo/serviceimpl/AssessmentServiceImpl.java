package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.stereotype.Service;

@Service
public class AssessmentServiceImpl implements AssessmentResultService {
    private final AssessmentResultRepository repository;

    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {
        // Validation for t008 and t041: must contain "Score"
        if (result.getScore() == null || result.getScore() < 0 || result.getScore() > 100) {
            throw new IllegalArgumentException("Invalid Score: Must be between 0 and 100");
        }
        return repository.save(result);
    }
}