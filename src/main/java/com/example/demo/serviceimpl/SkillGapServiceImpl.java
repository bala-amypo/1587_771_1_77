package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;
import com.example.demo.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillService skillService;
    private final AssessmentServiceImpl assessmentService;

    // Fix: Return type List<SkillGapRecord> to match interface contract
    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentId) {
        return skillService.getActiveSkills().stream()
            .map(skill -> {
                Double gap = calculateGap(studentId, skill);
                return new SkillGapRecord(skill.getName(), gap);
            })
            .collect(Collectors.toList());
    }

    @Override
    public Double calculateGap(Long studentId, Skill skill) {
        var results = assessmentService.getResultsByStudentAndSkill(studentId, skill.getId());
        if (results.isEmpty()) return skill.getMinCompetencyScore();

        double avg = results.stream().mapToDouble(r -> r.getScore()).average().orElse(0.0);
        double gap = skill.getMinCompetencyScore() - avg;
        return Math.max(0, gap);
    }
}