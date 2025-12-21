package com.example.demo.service.impl;

import com.example.demo.dto.RecommendationDTO;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public List<RecommendationDTO> generateRecommendations(Long studentId) {
        return new ArrayList<>();
    }

    @Override
    public List<RecommendationDTO> getRecommendationsByStudent(Long studentId) {
        return new ArrayList<>();
    }
}
