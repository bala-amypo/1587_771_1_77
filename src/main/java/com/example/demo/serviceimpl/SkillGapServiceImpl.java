package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.service.SkillGapService;
import com.example.demo.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillService skillService;
    private final AssessmentServiceImpl assessmentService;

    // Fixed: Implemented the missing contract method
    @Override
    public Map<String, Double> getGapsByStudent(Long studentId) {
        Map<String, Double> gaps = new HashMap<>();
        skillService.getActiveSkills().forEach(skill -> {
            gaps.put(skill.getName(), calculateGap(studentId, skill));
        });
        return gaps;
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