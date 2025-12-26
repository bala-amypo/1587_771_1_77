package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;
import java.util.List;

public interface AssessmentService {
    /**
     * Records a new assessment result.
     * Validates score presence and range as per tests t008 and t041.
     */
    AssessmentResult recordAssessment(AssessmentResult result);

    /**
     * Retrieves all results for a specific student.
     */
    List<AssessmentResult> getResultsByStudent(Long studentId);

    /**
     * Retrieves results filtered by both student and skill.
     * Required for Many-to-Many simulation and gap analysis in Topic 6.
     */
    List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId);
}