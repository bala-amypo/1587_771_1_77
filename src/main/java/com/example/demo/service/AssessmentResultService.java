package com.example.demo.service;

import com.example.demo.dto.AssessmentResultDTO;
import java.util.List;

public interface AssessmentResultService {
    AssessmentResultDTO createAssessmentResult(AssessmentResultDTO resultDTO);
    AssessmentResultDTO getAssessmentResultById(Long id);
    List<AssessmentResultDTO> getAllAssessmentResults();
    List<AssessmentResultDTO> getAssessmentResultsByUserId(Long userId);
    List<AssessmentResultDTO> getAssessmentResultsBySkillId(Long skillId);
    AssessmentResultDTO updateAssessmentResult(Long id, AssessmentResultDTO resultDTO);
    void deleteAssessmentResult(Long id);
}