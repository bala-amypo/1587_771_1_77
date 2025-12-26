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
        // Validation for test t041
        if (result.getScore() == null) {
            throw new IllegalArgumentException("Score cannot be null");
        }
        // Validation for test t008
        if (result.getMaxScore() != null && result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score cannot be greater than max score");
        }
        return assessmentResultRepository.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        return assessmentResultRepository.findByStudentProfileId(studentId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        return assessmentResultRepository.findByStudentProfileIdAndSkillId(studentId, skillId);
    }
}