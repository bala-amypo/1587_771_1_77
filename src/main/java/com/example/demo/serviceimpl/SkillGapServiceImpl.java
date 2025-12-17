package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository skillGapRecordRepository;
    private final SkillRepository skillRepository;
    private final AssessmentResultRepository assessmentResultRepository;

    public SkillGapServiceImpl(SkillGapRecordRepository skillGapRecordRepository,
                               SkillRepository skillRepository,
                               AssessmentResultRepository assessmentResultRepository) {
        this.skillGapRecordRepository = skillGapRecordRepository;
        this.skillRepository = skillRepository;
        this.assessmentResultRepository = assessmentResultRepository;
    }

    @Override
    public void computeGaps(Long studentProfileId) {
        skillGapRecordRepository.deleteByStudentProfileId(studentProfileId);

        List<Skill> activeSkills = skillRepository.findByActiveTrue();
        List<AssessmentResult> results = assessmentResultRepository.findByStudentProfileId(studentProfileId);

        Map<Long, Double> latestScores = results.stream()
                .collect(Collectors.toMap(
                        ar -> ar.getSkill().getId(),
                        AssessmentResult::getScoreObtained,
                        (s1, s2) -> s2 // keep later score
                ));

        Timestamp now = Timestamp.from(Instant.now());
        StudentProfile profile = new StudentProfile();
        profile.setId(studentProfileId);

        for (Skill skill : activeSkills) {
            Double current = latestScores.getOrDefault(skill.getId(), 0.0);
            Double target = skill.getMinCompetencyScore();
            Double gap = target - current;

            if (gap > 0) {
                SkillGapRecord record = new SkillGapRecord();
                record.setStudentProfile(profile);
                record.setSkill(skill);
                record.setCurrentScore(current);
                record.setTargetScore(target);
                record.setGapScore(gap);
                record.setCalculatedAt(now);
                skillGapRecordRepository.save(record);
            }
        }
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentProfileId) {
        return skillGapRecordRepository.findByStudentProfileId(studentProfileId);
    }
}
