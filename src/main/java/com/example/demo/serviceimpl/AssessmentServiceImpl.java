
package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;

public class AssessmentServiceImpl {

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
