package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private AssessmentResultRepository assessmentRepository;

    @Autowired
    private SkillGapRecordRepository gapRepository;

    @Autowired
    private StudentProfileRepository studentRepository;

    @Override
    public void computeSkillGaps(Long studentId) {

        // Fetch student
        StudentProfile student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Fetch all active skills
        List<Skill> skills = skillRepository.findByActiveTrue();

        for (Skill skill : skills) {

            Long skillId = skill.getId(); // define skillId

            // Fetch all assessment results for this student and skill
            List<AssessmentResult> results =
                    assessmentRepository.findByStudentProfile_IdAndSkill_Id(studentId, skillId);

            if (results.isEmpty()) continue;

            // Get latest result
            AssessmentResult latest = results.get(results.size() - 1);

            // Calculate gap
            double gap = skill.getMinCompetencyScore() - latest.getScoreObtained();

            if (gap > 0) {
                SkillGapRecord record = new SkillGapRecord();
                record.setStudentProfile(student);
                record.setSkill(skill);
                record.setCurrentScore(latest.getScoreObtained());
                record.setTargetScore(skill.getMinCompetencyScore());
                record.setGapScore(gap);
                record.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

                gapRepository.save(record);
            }
        }
    }
}
