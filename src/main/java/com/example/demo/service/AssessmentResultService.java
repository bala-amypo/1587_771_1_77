package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;
import java.util.List;

public interface AssessmentResultService {
    AssessmentResult recordAssessment(AssessmentResult result);
    List<AssessmentResult> getResultsByStudent(Long studentId);
    // Added to satisfy the compiler error
    List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId);
}