package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.entity.Skill;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillGapServiceImpl {

    private final AssessmentResultRepository assessmentRepository;
    private final SkillRepository skillRepository;

    public SkillGapServiceImpl(AssessmentResultRepository assessmentRepository,
                               SkillRepository skillRepository) {
        this.assessmentRepository = assessmentRepository;
        this.skillRepository = skillRepository;
    }

    public String calculateSkillGap(Long studentId, Long skillId) {

        Optional<AssessmentResult> optionalResult =
                assessmentRepository.findByStudentProfile_IdAndSkill_Id(studentId, skillId);

        if (optionalResult.isEmpty()) {
            return "No assessment found for this student and skill";
        }

        AssessmentResult result = optionalResult.get();

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        int gap = skill.getMinCompetencyScore() - result.getScoreObtained();

        if (gap <= 0) {
            return "No skill gap. Student meets required competency.";
        }

        return "Skill gap is: " + gap;
    }
}
