package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;
import java.util.List;

public interface AssessmentResultService {
    AssessmentResult recordResult(AssessmentResult result);
    List<AssessmentResult> getResultsByStudent(Long studentId);
    AssessmentResult getResultsByStudentAndSkill(Long studentId, Long skillId);
}