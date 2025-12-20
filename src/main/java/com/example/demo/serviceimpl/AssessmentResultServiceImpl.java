package com.example.demo.serviceimpl;

import com.example.demo.dto.AssessmentRequest;
import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentResultServiceImpl
        implements AssessmentResultService {

    @Autowired
    private AssessmentResultRepository repository;

    @Override
    public AssessmentResult saveAssessment(AssessmentRequest request) {
        AssessmentResult result = new AssessmentResult();
        result.setScoreObtained(request.getScoreObtained());
        result.setStudentId(request.getStudentId());
        result.setSkillId(request.getSkillId());
        result.setSubject(request.getSubject());
        return repository.save(result);
    }
}
