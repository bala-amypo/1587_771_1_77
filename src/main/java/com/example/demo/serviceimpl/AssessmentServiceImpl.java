package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentResultRepository assessmentResultRepository;

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {
        if (result.getScore() == null || result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Invalid Score"); [cite: 33, 34, 91]
        }
        return assessmentResultRepository.save(result); [cite: 31]
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        // This resolves the error: cannot find symbol findByStudentProfileId
        return assessmentResultRepository.findByStudentProfileId(studentId);
    }
}