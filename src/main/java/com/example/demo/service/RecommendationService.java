package com.example.demo.service;

import com.example.demo.dto.SkillGapRecommendationDTO;
import java.util.List;

public interface RecommendationService {
    SkillGapRecommendationDTO createRecommendation(SkillGapRecommendationDTO recommendationDTO);
    SkillGapRecommendationDTO getRecommendationById(Long id);
    List<SkillGapRecommendationDTO> getAllRecommendations();
    List<SkillGapRecommendationDTO> getRecommendationsByStudentId(Long userId);
    SkillGapRecommendationDTO updateRecommendation(Long id, SkillGapRecommendationDTO recommendationDTO);
    void deleteRecommendation(Long id);
    List<SkillGapRecommendationDTO> generateRecommendationsForStudent(Long userId);
}