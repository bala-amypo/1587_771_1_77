package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;
import org.springframework.stereotype.Service;

@Service //fix
public class AssessmentServiceImpl implements AssessmentService{

    private final AssessmentResultRepository repo;

    public AssessmentServiceImpl(AssessmentResultRepository repo) {
        this.repo = repo;
    }

    public AssessmentResult recordAssessment(AssessmentResult r) {
        if (r.getScore() == null ||
            r.getScore() < 0 ||
            r.getScore() > r.getMaxScore()) {

            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        return repo.save(r);
    }
}
