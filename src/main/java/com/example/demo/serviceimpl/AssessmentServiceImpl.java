// package com.example.demo.serviceimpl;

// import com.example.demo.entity.AssessmentResult;
// import com.example.demo.repository.AssessmentResultRepository;
// import com.example.demo.service.AssessmentService;
// import org.springframework.stereotype.Service;

// @Service 
// public class AssessmentServiceImpl implements AssessmentService{

//     private final AssessmentResultRepository repo;

//     public AssessmentServiceImpl(AssessmentResultRepository repo) {
//         this.repo = repo;
//     }

//     public AssessmentResult recordAssessment(AssessmentResult r) {
//         if (r.getScore() == null ||
//             r.getScore() < 0 ||
//             r.getScore() > r.getMaxScore()) {

//             throw new IllegalArgumentException("Score must be between 0 and 100");
//         }

//         return repo.save(r);
//     }
// }


package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentResultRepository repo;

    // Required constructor injection 
    public AssessmentServiceImpl(AssessmentResultRepository repo) {
        this.repo = repo;
    }

    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {
        // Validation Rule: scoreObtained ≥ 0 and ≤ maxScore [cite: 30]
        // Throws IllegalArgumentException for invalid or null scores [cite: 79, 80]
        if (result.getScore() == null || result.getScore() < 0 || result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        return repo.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        // Fetches all results by student profile ID [cite: 56, 80]
        return repo.findByStudentProfileId(studentId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        // Fetches results for a specific student and skill [cite: 57, 80]
        return repo.findByStudentProfileIdAndSkillId(studentId, skillId);
    }
}
