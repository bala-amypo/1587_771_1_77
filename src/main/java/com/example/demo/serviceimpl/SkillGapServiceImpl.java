package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SkillGapServiceImpl implements SkillGapService {
    private final SkillGapRecordRepository recordRepo;
    private final SkillRepository skillRepo;
    private final AssessmentResultRepository assessmentRepo;

    public SkillGapServiceImpl(SkillGapRecordRepository recordRepo, SkillRepository skillRepo, AssessmentResultRepository assessmentRepo) {
        this.recordRepo = recordRepo;
        this.skillRepo = skillRepo;
        this.assessmentRepo = assessmentRepo;
    }

    @Override
    public List<SkillGapRecord> computeGaps(Long studentId) {
        List<Skill> activeSkills = skillRepo.findByActiveTrue();
        List<SkillGapRecord> gaps = new ArrayList<>();
        
        for (Skill skill : activeSkills) {
            List<AssessmentResult> results = assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skill.getId());
            double latestScore = results.isEmpty() ? 0.0 : results.get(results.size() - 1).getScore();
            
            SkillGapRecord record = SkillGapRecord.builder()
                .studentProfile(StudentProfile.builder().id(studentId).build())
                .skill(skill)
                .currentScore(latestScore)
                .targetScore(skill.getMinCompetencyScore())
                .gapScore(skill.getMinCompetencyScore() - latestScore)
                .build();
            gaps.add(recordRepo.save(record));
        }
        return gaps;
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentId) {
        return recordRepo.findByStudentProfileId(studentId);
    }
}