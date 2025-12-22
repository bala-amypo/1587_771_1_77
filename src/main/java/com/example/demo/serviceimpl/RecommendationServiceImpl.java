package com.example.demo.serviceimpl;

import com.example.demo.dto.RecommendationDTO;
import com.example.demo.entity.Priority;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public List<RecommendationDTO> getRecommendationsByStudent(Long studentId) {

        List<RecommendationDTO> recommendations = new ArrayList<>();

        RecommendationDTO dto = new RecommendationDTO();
        dto.setSkillName("Spring Boot");
        dto.setPriority(Priority.HIGH);
        dto.setRecommendation("Practice REST APIs, JPA mappings, and exception handling");

        recommendations.add(dto);

        return recommendations;
    }
}
