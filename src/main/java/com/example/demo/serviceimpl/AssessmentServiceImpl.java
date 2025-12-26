package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService; // Ensure this is imported
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository assessmentResultRepository;

    public AssessmentServiceImpl(AssessmentResultRepository assessmentResultRepository) {
        this.assessmentResultRepository = assessmentResultRepository;
    }

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {
        // Add basic validation as required by your test t008
        if (result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score cannot exceed Max Score");
        }
        if (result.getScore() == null) {
            throw new IllegalArgumentException("Score is required");
        }
        return assessmentResultRepository.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        return assessmentResultRepository.findByStudentProfileId(studentId);
    }

    // This is the specific method causing the "is not abstract" error
    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        // Ensure your Repository has findByStudentProfileIdAndSkillId defined
        return assessmentResultRepository.findByStudentProfileIdAndSkillId(studentId, skillId);
    }
}