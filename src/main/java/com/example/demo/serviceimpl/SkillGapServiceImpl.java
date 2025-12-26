package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository gapRepository;
    private final SkillRepository skillRepository;
    private final AssessmentResultRepository assessmentRepository;

    public SkillGapServiceImpl(
            SkillGapRecordRepository gapRepository,
            SkillRepository skillRepository,
            AssessmentResultRepository assessmentRepository) {

        this.gapRepository = gapRepository;
        this.skillRepository = skillRepository;
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public List<SkillGapRecord> computeGaps(Long studentProfileId) {

        List<Skill> skills = skillRepository.findByActiveTrue();
        List<SkillGapRecord> records = new ArrayList<>();

        for (Skill s : skills) {

            List<AssessmentResult> results =
                    assessmentRepository.findByStudentProfileIdAndSkillId(studentProfileId, s.getId());

            double current = results.stream()
                    .mapToDouble(AssessmentResult::getScore)
                    .max()
                    .orElse(0.0);

            double target = s.getMinCompetencyScore() == null
                    ? 100.0
                    : s.getMinCompetencyScore();

            SkillGapRecord rec = SkillGapRecord.builder()
                    .studentProfile(StudentProfile.builder().id(studentProfileId).build())
                    .skill(s)
                    .currentScore(current)
                    .targetScore(target)
                    .gapScore(target - current)
                    .calculatedAt(Instant.now())
                    .build();

            records.add(gapRepository.save(rec));
        }

        return records;
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentId) {
        return gapRepository.findByStudentProfileId(studentId);
    }
}
