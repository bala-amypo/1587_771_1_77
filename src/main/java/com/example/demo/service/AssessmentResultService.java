package com.example.demo.service;

import com.example.demo.dto.AssessmentResultDTO;

import java.util.List;

public interface AssessmentResultService {
    AssessmentResultDTO createAssessment(AssessmentResultDTO resultDTO);
    AssessmentResultDTO getAssessmentById(Long id);
    List<AssessmentResultDTO> getAllAssessments();
    List<AssessmentResultDTO> getAssessmentsByStudentId(Long userId);
    List<AssessmentResultDTO> getAssessmentsBySkillId(Long skillId);
    AssessmentResultDTO updateAssessment(Long id, AssessmentResultDTO resultDTO);
    void deleteAssessment(Long id);
}