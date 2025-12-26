package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    @Autowired
    private AssessmentResultRepository assessmentResultRepository;

    @Override
    public AssessmentResult saveResult(AssessmentResult result) {
        return assessmentResultRepository.save(result);
    }

    @Override
    public List<AssessmentResult> getAllResults() {
        return assessmentResultRepository.findAll();
    }

    @Override
    public Double getAverageScoreByCohort(String cohort, Long skillId) {
        return assessmentResultRepository.avgScoreByCohortAndSkill(cohort, skillId);
    }
}