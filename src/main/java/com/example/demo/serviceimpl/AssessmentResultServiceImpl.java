package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository assessmentResultRepository;

    public AssessmentResultServiceImpl(AssessmentResultRepository assessmentResultRepository) {
        this.assessmentResultRepository = assessmentResultRepository;
    }

    @Override
    public AssessmentResult saveAssessmentResult(AssessmentResult assessmentResult) {
        return assessmentResultRepository.save(assessmentResult);
    }

    @Override
    public List<AssessmentResult> getAllAssessmentResults() {
        return assessmentResultRepository.findAll();
    }

    @Override
    public List<AssessmentResult> getResultsByStudentId(Long studentId) {
        return assessmentResultRepository.findByStudentProfile_Id(studentId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        return assessmentResultRepository.findByStudentProfile_IdAndSkill_Id(studentId, skillId);
    }
}
