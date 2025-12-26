// package com.example.demo.serviceimpl;

// import com.example.demo.entity.AssessmentResult;
// import com.example.demo.repository.AssessmentResultRepository;
// import com.example.demo.service.AssessmentResultService; // Fixed import
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class AssessmentServiceImpl implements AssessmentResultService { // Fixed interface name

//     private final AssessmentResultRepository assessmentResultRepository;

//     public AssessmentServiceImpl(AssessmentResultRepository assessmentResultRepository) {
//         this.assessmentResultRepository = assessmentResultRepository;
//     }

//     @Override
//     public AssessmentResult recordAssessment(AssessmentResult result) {
//         // Fix for Test t041: Check for Null
//         if (result.getScore() == null) {
//             throw new IllegalArgumentException("Score cannot be null");
//         }

//         // Fix for Test t008: Check for boundaries
//         // We use 100.0 as default if maxScore isn't provided, to avoid NullPointerException
//         double max = (result.getMaxScore() != null) ? result.getMaxScore() : 100.0;

//         if (result.getScore() < 0 || result.getScore() > max) {
//             throw new IllegalArgumentException("Score is out of valid range (0 to " + max + ")");
//         }

//         return assessmentResultRepository.save(result);
//     }

//     @Override
//     public List<AssessmentResult> getResultsByStudent(Long studentId) {
//         // Uses the HQL method required by test t031
//         return assessmentResultRepository.findRecentByStudent(studentId);
//     }

//     @Override
//     public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
//         // Matches the method name in your AssessmentResultService interface
//         return assessmentResultRepository.findByStudentProfileIdAndSkillId(studentId, skillId);
//     }

//     // This remains to support HQL tests like t030 and t058
//     public Double getAverageScore(String cohort, Long skillId) {
//         return assessmentResultRepository.avgScoreByCohortAndSkill(cohort, skillId);
//     }
// }
package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;

public class AssessmentServiceImpl implements AssessmentResultService {
    private final AssessmentResultRepository repository;

    public AssessmentServiceImpl(AssessmentResultRepository repository) { this.repository = repository; }

    public AssessmentResult recordAssessment(AssessmentResult result) {
        if (result.getScore() == null) throw new IllegalArgumentException("Score is required");
        if (result.getScore() < 0 || result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score out of bounds");
        }
        return repository.save(result);
    }
}