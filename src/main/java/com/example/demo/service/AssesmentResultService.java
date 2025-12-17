package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AssessmentResultService {

    private final AssessmentResultRepository assessmentResultRepository;

    public AssessmentResultService(AssessmentResultRepository assessmentResultRepository) {
        this.assessmentResultRepository = assessmentResultRepository;
    }

    public AssessmentResult recordResult(AssessmentResult result) {
        Double obtained = result.getScoreObtained();
        Double max = result.getMaxScore();

        if (obtained == null || max == null || obtained < 0 || obtained > max) {
            throw new IllegalArgumentException("Score must be between 0 and " + max);
        }

        if (result.getAssessedAt() == null) {
            result.setAssessedAt(new Timestamp(System.currentTimeMillis()));
        }

        return assessmentResultRepository.save(result);
    }

    public List<AssessmentResult> getResultsByStudent(Long studentProfileId) {
        return assessmentResultRepository.findByStudentProfileId(studentProfileId);
    }

    public AssessmentResult getResultsByStudentAndSkill(Long studentProfileId, Long skillId) {
        return assessmentResultRepository.findByStudentProfileId(studentProfileId).stream()
                .filter(ar -> ar.getSkill().getId().equals(skillId))
                .max((a1, a2) -> a2.getAssessedAt().compareTo(a1.getAssessedAt())) // latest
                .orElseThrow(() -> new ResourceNotFoundException("Assessment not found"));
    }
}
