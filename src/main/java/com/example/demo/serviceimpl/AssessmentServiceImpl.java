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
        // Requirement for t041: recordAssessmentNullScore
        if (result.getScore() == null) {
            throw new IllegalArgumentException("Score cannot be null");
        }

        // Requirement for t008: recordAssessmentInvalidScore
        // Test expects an IllegalArgumentException containing the word "Score"
        if (result.getMaxScore() != null && result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score exceeds the maximum possible value");
        }

        return assessmentResultRepository.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        return assessmentResultRepository.findByStudentProfileId(studentId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        // Supports the many-to-many relationship simulation in t024 and t025
        return assessmentResultRepository.findByStudentProfileIdAndSkillId(studentId, skillId);
    }
}