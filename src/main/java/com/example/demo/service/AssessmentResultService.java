package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;

import java.util.List;

public interface AssessmentResultService {

    AssessmentResult createAssessment(AssessmentResult result);

    List<AssessmentResult> getAssessmentsByStudent(Long studentProfileId);

    AssessmentResult getAssessmentById(Long id);
}
