package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final StudentProfileRepository studentRepo;
    private final AssessmentResultRepository assessmentRepo;
    private final SkillGapRecordRepository gapRepo;

    public SkillGapServiceImpl(StudentProfileRepository studentRepo,
                               AssessmentResultRepository assessmentRepo,
                               SkillGapRecordRepository gapRepo) {
        this.studentRepo = studentRepo;
        this.assessmentRepo = assessmentRepo;
        this.gapRepo = gapRepo;
    }

    @Override
    public List<SkillGapRecord> computeSkillGaps(Long studentId) {

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileId(studentId);

        Map<Skill, List<AssessmentResult>> grouped =
                results.stream().collect(
                        java.util.stream.Collectors.groupingBy(AssessmentResult::getSkill)
                );

        List<SkillGapRecord> savedRecords = new ArrayList<>();

        for (Skill skill : grouped.keySet()) {

            double avgScore = grouped.get(skill)
                    .stream()
                    .mapToDouble(AssessmentResult::getScoreObtained)
                    .average()
                    .orElse(0);

            double target = skill.getMinCompetencyScore();
            double gap = target - avgScore;

            SkillGapRecord record = new SkillGapRecord();
            record.setStudentProfile(student);
            record.setSkill(skill);
            record.setCurrentScore(avgScore);
            record.setTargetScore(target);
            record.setGapScore(gap);

            savedRecords.add(gapRepo.save(record));
        }

        return savedRecords;
    }
}
