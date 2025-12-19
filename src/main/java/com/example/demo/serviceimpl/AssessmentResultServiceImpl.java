package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository repository;

    public AssessmentResultServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentResult saveAssessmentResult(AssessmentResult assessmentResult) {
        return repository.save(assessmentResult);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentId(Long studentId) {
        return repository.findByStudentProfile_Id(studentId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        return repository.findByStudentProfile_IdAndSkill_Id(studentId, skillId);
    }
}
