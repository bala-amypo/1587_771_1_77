package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;
import java.util.List;

public interface AssessmentResultService {

    AssessmentResult saveAssessmentResult(AssessmentResult assessmentResult);

    List<AssessmentResult> getResultsByStudentId(Long studentId);

    List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId);
}
