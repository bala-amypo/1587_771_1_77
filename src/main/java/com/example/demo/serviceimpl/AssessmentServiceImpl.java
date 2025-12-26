package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;
import org.springframework.stereotype.Service;

@Service
public class AssessmentServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository repository;

    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {

        // validation required by tests
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
}
