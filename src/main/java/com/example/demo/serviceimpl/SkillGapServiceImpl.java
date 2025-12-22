package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<SkillGapRecord> computeGaps(Long studentId) {

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileId(studentId);

        Map<Skill, List<AssessmentResult>> grouped =
                results.stream()
                        .collect(Collectors.groupingBy(AssessmentResult::getSkill));

        List<SkillGapRecord> records = new ArrayList<>();

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

            records.add(gapRepo.save(record));
        }

        return records;
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentId) {
        return gapRepo.findByStudentProfileId(studentId);
    }
}
