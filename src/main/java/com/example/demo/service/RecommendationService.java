package com.example.demo.service;

import com.example.demo.dto.RecommendationDTO;
import java.util.List;

public interface RecommendationService {

    List<RecommendationDTO> generateRecommendations(Long studentId);

    List<RecommendationDTO> getRecommendationsByStudent(Long studentId);
}
