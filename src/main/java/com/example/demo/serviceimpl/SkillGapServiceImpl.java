// src/main/java/com/example/demo/serviceimpl/SkillGapServiceImpl.java
package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;
import com.example.demo.service.SkillService;
import com.example.demo.service.AssessmentService; // Ensure this exists and is imported
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillService skillService;
    private final AssessmentService assessmentService; // Injecting the interface

    @Override
    public List<SkillGapRecord> computeGaps(Long studentId) {
        return skillService.getActiveSkills().stream()
            .map(skill -> {
                Double gap = calculateGap(studentId, skill);
                return new SkillGapRecord(skill, gap);
            })
            .collect(Collectors.toList());
    }

    @Override
    public Double calculateGap(Long studentId, Skill skill) {
        var results = assessmentService.getResultsByStudentAndSkill(studentId, skill.getId());
        
        if (results.isEmpty()) {
            return skill.getMinCompetencyScore();
        }

        double averageScore = results.stream()
                .mapToDouble(r -> r.getScore())
                .average()
                .orElse(0.0);

        double gap = skill.getMinCompetencyScore() - averageScore;
        return Math.max(0.0, gap);
    }
}