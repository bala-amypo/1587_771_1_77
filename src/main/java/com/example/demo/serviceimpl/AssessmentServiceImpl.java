package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository assessmentResultRepository;

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {
        if (result.getScore() == null || result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score cannot be null or greater than max score");
        }
        return assessmentResultRepository.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        return assessmentResultRepository.findByStudentProfileId(studentId);
    }
}