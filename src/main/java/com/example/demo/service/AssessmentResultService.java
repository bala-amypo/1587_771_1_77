package com.example.demo.service;

import com.example.demo.entity.AssessmentResult;

import java.util.List;

public interface AssessmentResultService {

    AssessmentResult recordResult(AssessmentResult result);

    List<AssessmentResult> getResultsByStudent(Long studentProfileId);

    List<AssessmentResult> getResultsByStudentAndSkill(Long studentProfileId, Long skillId);
}
