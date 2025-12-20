package com.example.demo.service;

import com.example.demo.dto.AssessmentRequest;
import com.example.demo.entity.AssessmentResult;

public interface AssessmentResultService {

    AssessmentResult saveAssessment(AssessmentRequest request);
}
