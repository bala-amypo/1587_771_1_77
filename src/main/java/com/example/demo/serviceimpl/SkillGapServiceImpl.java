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
        // Fetch results for simulation of Many-to-Many associations [cite: 46]
        List<AssessmentResult> results = assessmentResultRepository
                .findByStudentProfileIdAndSkillId(studentId, skill.getId());

        if (results.isEmpty()) {
            return skill.getMinCompetencyScore();
        }

        double averageScore = results.stream()
                .mapToDouble(AssessmentResult::getScore)
                .average()
                .orElse(0.0);

        // Gap calculation logic [cite: 47]
        double gap = skill.getMinCompetencyScore() - averageScore;
        return gap > 0 ? gap : 0.0;
    }
}