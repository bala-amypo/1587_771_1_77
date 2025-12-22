package com.example.demo.service;

import com.example.demo.dto.RecommendationDTO;
import java.util.List;

public interface RecommendationService {

    List<RecommendationDTO> getRecommendationsByStudent(Long studentId);
}
