package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;
import java.util.List;

public interface AssessmentResultService {
    AssessmentResult recordAssessment(AssessmentResult result);
    List<AssessmentResult> getResultsByStudent(Long studentId);
    
    // Required for many-to-many simulation tests t024, t025, and t045
    List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId);
}