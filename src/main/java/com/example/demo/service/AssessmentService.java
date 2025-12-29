
// package com.example.demo.service;

// import com.example.demo.entity.AssessmentResult;

// public interface AssessmentService {
//     AssessmentResult recordAssessment(AssessmentResult result);
// }




package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;
import java.util.List;

public interface AssessmentService {
    // Record assessment result [cite: 79]
    AssessmentResult recordAssessment(AssessmentResult result);

    // Get all results for student [cite: 80]
    List<AssessmentResult> getResultsByStudent(Long studentId);

    // Get results for student and specific skill [cite: 80]
    List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId);
}