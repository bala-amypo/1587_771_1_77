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

    private final AssessmentResultRepository repository;

    public AssessmentResultServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentResult recordResult(AssessmentResult result) {
        if (result.getScoreObtained() < 0 || result.getScoreObtained() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score must be between 0 and " + result.getMaxScore());
        }
        if (result.getAssessedAt() == null) {
            result.setAssessedAt(new Timestamp(System.currentTimeMillis()));
        }
        return repository.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentProfileId) {
        return repository.findByStudentProfileId(studentProfileId);
    }

    @Override
    public AssessmentResult getResultsByStudentAndSkill(Long studentProfileId, Long skillId) {
        return repository.findByStudentProfileId(studentProfileId).stream()
                .filter(ar -> ar.getSkill().getId().equals(skillId))
                .max((a1, a2) -> a2.getAssessedAt().compareTo(a1.getAssessedAt()))
                .orElseThrow(() -> new ResourceNotFoundException("Assessment not found"));
    }
}