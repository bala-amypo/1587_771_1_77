package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.entity.Skill;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.SkillGapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillGapServiceImpl implements SkillGapService {

    private final AssessmentResultRepository assessmentResultRepository;

    @Override
    public Double calculateGap(Long studentId, Skill skill) {
        // Fetch assessments for the specific student and skill
        List<AssessmentResult> results = assessmentResultRepository
                .findByStudentProfileIdAndSkillId(studentId, skill.getId()); [cite: 88, 89]

        if (results.isEmpty()) {
            return skill.getMinCompetencyScore(); // The gap is the full required score if no tests taken
        }

        // Calculate average score
        double averageScore = results.stream()
                .mapToDouble(AssessmentResult::getScore)
                .average()
                .orElse(0.0);

        // Gap = Required Min Competency - Actual Average Score
        // Resolves "cannot find symbol" by accessing the new entity field
        double gap = skill.getMinCompetencyScore() - averageScore;
        
        return gap > 0 ? gap : 0.0; // Return 0 if the student exceeds competency
    }
}