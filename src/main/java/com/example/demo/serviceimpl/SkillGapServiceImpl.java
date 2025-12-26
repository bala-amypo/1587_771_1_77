package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {
    private final SkillGapRecordRepository skillGapRecordRepository;
    private final SkillRepository skillRepository;
    private final AssessmentResultRepository assessmentResultRepository;

    public SkillGapServiceImpl(SkillGapRecordRepository sgr, SkillRepository sr, AssessmentResultRepository ar) {
        this.skillGapRecordRepository = sgr;
        this.skillRepository = sr;
        this.assessmentResultRepository = ar;
    }

    @Override
    public List<SkillGapRecord> computeGaps(Long studentProfileId) {
        List<Skill> activeSkills = skillRepository.findByActiveTrue();
        List<SkillGapRecord> gaps = new ArrayList<>();
        
        for (Skill skill : activeSkills) {
            List<AssessmentResult> results = assessmentResultRepository.findByStudentProfileIdAndSkillId(studentProfileId, skill.getId());
            double latestScore = results.stream()
                    .mapToDouble(AssessmentResult::getScore)
                    .findFirst().orElse(0.0);
            
            double target = skill.getMinCompetencyScore() != null ? skill.getMinCompetencyScore() : 80.0;
            
            SkillGapRecord record = SkillGapRecord.builder()
                    .studentProfile(StudentProfile.builder().id(studentProfileId).build())
                    .skill(skill)
                    .currentScore(latestScore)
                    .targetScore(target)
                    .gapScore(target - latestScore)
                    .build();
            
            gaps.add(skillGapRecordRepository.save(record));
        }
        return gaps;
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentId) {
        return skillGapRecordRepository.findByStudentProfileId(studentId);
    }
}