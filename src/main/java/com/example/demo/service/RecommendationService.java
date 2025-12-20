package com.example.demo.service;

import com.example.demo.dto.SkillGapRecommendationDTO;
import java.util.List;

public interface RecommendationService {
    SkillGapRecommendationDTO createRecommendation(SkillGapRecommendationDTO recommendationDTO);
    SkillGapRecommendationDTO getRecommendationById(Long id);
    List<SkillGapRecommendationDTO> getAllRecommendations();
    List<SkillGapRecommendationDTO> getRecommendationsByUserId(Long userId);
    List<SkillGapRecommendationDTO> getRecommendationsByPriority(String priority);
    SkillGapRecommendationDTO updateRecommendation(Long id, SkillGapRecommendationDTO recommendationDTO);
    void deleteRecommendation(Long id);
}
