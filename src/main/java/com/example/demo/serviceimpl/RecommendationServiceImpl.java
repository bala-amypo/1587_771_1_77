
// package com.example.demo.serviceimpl;

// import com.example.demo.entity.Skill;
// import com.example.demo.entity.SkillGapRecommendation;
// import com.example.demo.entity.StudentProfile;
// import com.example.demo.repository.AssessmentResultRepository;
// import com.example.demo.repository.SkillGapRecommendationRepository;
// import com.example.demo.repository.SkillRepository;
// import com.example.demo.repository.StudentProfileRepository;

// import java.util.ArrayList;
// import java.util.List;

// public class RecommendationServiceImpl {

//     private final AssessmentResultRepository arRepo;
//     private final SkillGapRecommendationRepository recRepo;
//     private final StudentProfileRepository spRepo;
//     private final SkillRepository skillRepo;

//     public RecommendationServiceImpl(
//             AssessmentResultRepository arRepo,
//             SkillGapRecommendationRepository recRepo,
//             StudentProfileRepository spRepo,
//             SkillRepository skillRepo) {

//         this.arRepo = arRepo;
//         this.recRepo = recRepo;
//         this.spRepo = spRepo;
//         this.skillRepo = skillRepo;
//     }

//     public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {

//         StudentProfile sp = spRepo.findById(studentId).orElseThrow();
//         Skill skill = skillRepo.findById(skillId).orElseThrow();

//         SkillGapRecommendation recommendation = SkillGapRecommendation.builder()
//                 .studentProfile(sp)
//                 .skill(skill)
//                 .gapScore(50.0)
//                 .priority("MEDIUM")
//                 .recommendedAction("Practice")
//                 .generatedBy("SYSTEM")
//                 .build();

//         return recRepo.save(recommendation);
//     }

//     public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {

//         spRepo.findById(studentId).orElseThrow();

//         List<Skill> skills = skillRepo.findByActiveTrue();
//         List<SkillGapRecommendation> result = new ArrayList<>();

//         for (Skill skill : skills) {
//             result.add(computeRecommendationForStudentSkill(studentId, skill.getId()));
//         }

//         return result;
//     }

//     public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
//         return recRepo.findByStudentOrdered(studentId);
//     }
// }




package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository arRepo;
    private final SkillGapRecommendationRepository recRepo;
    private final StudentProfileRepository spRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(
            AssessmentResultRepository arRepo,
            SkillGapRecommendationRepository recRepo,
            StudentProfileRepository spRepo,
            SkillRepository skillRepo) {

        this.arRepo = arRepo;
        this.recRepo = recRepo;
        this.spRepo = spRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId) {
        StudentProfile sp = spRepo.findById(studentId).orElseThrow();
        Skill skill = skillRepo.findById(skillId).orElseThrow();

        // Calculate latest gap: Target - latest assessment score
        List<AssessmentResult> results = arRepo.findByStudentProfileIdAndSkillId(studentId, skillId);
        double latestScore = results.isEmpty() ? 0.0 : results.get(0).getScore();
        double target = (skill.getMinCompetencyScore() != null) ? skill.getMinCompetencyScore() : 70.0;
        double gap = target - latestScore;

        // Section 2.6 Priority Rule: Gap >= 20 -> HIGH, else MEDIUM/LOW
        String priority = "LOW";
        String action = "General Review";
        
        if (gap >= 20.0) {
            priority = "HIGH";
            action = "Intensive Practice & Tutoring Required";
        } else if (gap > 0) {
            priority = "MEDIUM";
            action = "Practice Exercises";
        }

        SkillGapRecommendation recommendation = SkillGapRecommendation.builder()
                .studentProfile(sp)
                .skill(skill)
                .gapScore(gap > 0 ? gap : 0.0)
                .priority(priority)
                .recommendedAction(action)
                .generatedBy("SYSTEM")
                .build();

        return recRepo.save(recommendation);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId) {
        spRepo.findById(studentId).orElseThrow();
        List<Skill> skills = skillRepo.findByActiveTrue();
        List<SkillGapRecommendation> result = new ArrayList<>();

        for (Skill skill : skills) {
            // Re-using the single skill logic ensures consistency across test cases
            result.add(computeRecommendationForStudentSkill(studentId, skill.getId()));
        }

        return result;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        // Aligns with test case t038 using specific repository query
        return recRepo.findByStudentOrdered(studentId);
    }
}