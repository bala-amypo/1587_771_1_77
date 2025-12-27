
// package com.example.demo.serviceimpl;

// import com.example.demo.entity.AssessmentResult;
// import com.example.demo.repository.AssessmentResultRepository;

// public class AssessmentServiceImpl {

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

    // Section 6.3 Requirement: Constructor injection
    public AssessmentServiceImpl(AssessmentResultRepository repo) {
        this.repo = repo;
    }

    @Override
    public AssessmentResult recordAssessment(AssessmentResult r) {
        // Section 2.4 Rules: Default maxScore if null
        if (r.getMaxScore() == null) {
            r.setMaxScore(100.0);
        }

        // Section 6.3 & 5 Rules: Validation logic
        if (r.getScore() == null ||
            r.getScore() < 0 ||
            r.getScore() > r.getMaxScore()) {

            // Section 5 Requirement: Message must contain 'Score' and range
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        return repo.save(r);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        return repo.findByStudentProfileId(studentId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        return repo.findByStudentProfileIdAndSkillId(studentId, skillId);
    }
}