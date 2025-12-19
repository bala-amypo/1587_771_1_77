package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;
import java.util.List;

public interface AssessmentResultService {

    AssessmentResult saveAssessmentResult(
            String subject,
            double scoreObtained,
            Long studentId,
            Long skillId
    );

    List<AssessmentResult> getResultsByStudentId(Long studentId);

    List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId);
}
