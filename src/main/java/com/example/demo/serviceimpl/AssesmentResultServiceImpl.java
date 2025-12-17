package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository assessmentResultRepository;

    public AssessmentResultServiceImpl(AssessmentResultRepository assessmentResultRepository) {
        this.assessmentResultRepository = assessmentResultRepository;
    }

    @Override
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

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentProfileId) {
        return assessmentResultRepository.findByStudentProfileId(studentProfileId);
    }

    @Override
    public AssessmentResult getResultsByStudentAndSkill(Long studentProfileId, Long skillId) {
        return assessmentResultRepository.findByStudentProfileId(studentProfileId).stream()
                .filter(ar -> ar.getSkill().getId().equals(skillId))
                .max((a1, a2) -> a2.getAssessedAt().compareTo(a1.getAssessedAt()))
                .orElseThrow(() -> new ResourceNotFoundException("Assessment not found"));
    }
}
