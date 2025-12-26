package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final AssessmentResultRepository assessmentResultRepository;
    private final SkillGapRecommendationRepository recommendationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    public RecommendationServiceImpl(AssessmentResultRepository ar, SkillGapRecommendationRepository rr, 
                                     StudentProfileRepository spr, SkillRepository sr) {
        this.assessmentResultRepository = ar;
        this.recommendationRepository = rr;
        this.studentProfileRepository = spr;
        this.skillRepository = sr;
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {
        StudentProfile profile = studentProfileRepository.findById(studentId).orElseThrow();
        Skill skill = skillRepository.findById(id).orElseThrow();
        
        List<AssessmentResult> results = assessmentResultRepository.findByStudentProfileIdAndSkillId(studentId, skillId);
        double avgScore = results.stream().mapToDouble(AssessmentResult::getScore).average().orElse(0.0);
        double gap = (skill.getMinCompetencyScore() != null ? skill.getMinCompetencyScore() : 80.0) - avgScore;

        String priority = gap >= 20 ? "HIGH" : (gap > 0 ? "MEDIUM" : "LOW");
        
        SkillGapRecommendation rec = SkillGapRecommendation.builder()
                .studentProfile(profile)
                .skill(skill)
                .gapScore(gap)
                .priority(priority)
                .recommendedAction(priority.equals("HIGH") ? "Intensive Training" : "Standard Review")
                .generatedBy("SYSTEM")
                .build();
                
        return recommendationRepository.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {
        List<Skill> skills = skillRepository.findByActiveTrue();
        List<SkillGapRecommendation> list = new ArrayList<>();
        for (Skill s : skills) {
            list.add(computeRecommendationForStudentSkill(studentId, s.getId()));
        }
        return list;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return recommendationRepository.findByStudentOrdered(studentId); // 
    }
}