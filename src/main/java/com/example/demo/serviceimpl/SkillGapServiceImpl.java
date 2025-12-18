package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository gapRepository;
    private final SkillRepository skillRepository;
    private final AssessmentResultRepository assessmentRepository;

    public SkillGapServiceImpl(SkillGapRecordRepository gapRepository,
                               SkillRepository skillRepository,
                               AssessmentResultRepository assessmentRepository) {
        this.gapRepository = gapRepository;
        this.skillRepository = skillRepository;
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public void computeGaps(Long studentProfileId) {
        gapRepository.deleteByStudentProfileId(studentProfileId);

        List<Skill> activeSkills = skillRepository.findByActiveTrue();
        List<AssessmentResult> results = assessmentRepository.findByStudentProfileId(studentProfileId);

        Map<Long, Double> latestScores = new HashMap<>();
        for (AssessmentResult ar : results) {
            latestScores.put(ar.getSkill().getId(), ar.getScoreObtained());
        }

        Timestamp now = Timestamp.from(Instant.now());
        StudentProfile profile = new StudentProfile();
        profile.setId(studentProfileId);

        for (Skill skill : activeSkills) {
            Double current = latestScores.getOrDefault(skill.getId(), 0.0);
            Double gap = skill.getMinCompetencyScore() - current;
            if (gap > 0) {
                SkillGapRecord record = new SkillGapRecord();
                record.setStudentProfile(profile);
                record.setSkill(skill);
                record.setCurrentScore(current);
                record.setTargetScore(skill.getMinCompetencyScore());
                record.setGapScore(gap);
                record.setCalculatedAt(now);
                gapRepository.save(record);
            }
        }
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentProfileId) {
        return gapRepository.findByStudentProfileId(studentProfileId);
    }
}