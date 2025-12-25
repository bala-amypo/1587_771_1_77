package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;
import java.util.List;

public interface AssessmentResultService {
    AssessmentResult saveResult(AssessmentResult result);
    List<AssessmentResult> getResultsByStudent(Long studentId);
}
