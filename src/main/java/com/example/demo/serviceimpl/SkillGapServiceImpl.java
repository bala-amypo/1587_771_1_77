package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final AssessmentResultRepository assessmentRepo;
    private final SkillGapRecordRepository gapRepo;

    public SkillGapServiceImpl(
            AssessmentResultRepository assessmentRepo,
            SkillGapRecordRepository gapRepo
    ) {
        this.assessmentRepo = assessmentRepo;
        this.gapRepo = gapRepo;
    }

    @Override
    public SkillGapRecord computeGap(Long studentId, Long skillId) {

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skillId);

        Double averageScore = results.stream()
                .map(r -> r.getScoreObtained())   // ✅ FIXED
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        SkillGapRecord gap = SkillGapRecord.builder()
                .studentProfileId(studentId)
                .skillId(skillId)
                .averageScore(averageScore)
                .gapLevel(determineGapLevel(averageScore))
                .build();

        return gapRepo.save(gap);
    }

    private String determineGapLevel(Double score) {
        if (score >= 85) return "NO_GAP";
        if (score >= 60) return "LOW";
        if (score >= 40) return "MEDIUM";
        return "HIGH";
    }
}
