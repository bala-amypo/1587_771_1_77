package com.example.demo.serviceimpl;

import com.example.demo.dto.AssessmentRequest;
import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;

import org.springframework.stereotype.Service;

@Service
public class AssessmentResultServiceImpl
        implements AssessmentResultService {

    private final AssessmentResultRepository repository;

    public AssessmentResultServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentResult saveAssessment(AssessmentRequest request) {
        AssessmentResult result = new AssessmentResult();
        result.setStudentId(request.getStudentId());
        result.setSkillId(request.getSkillId());
        result.setScore(request.getScore());

        return repository.save(result);
    }
}
