package com.example.demo.serviceimpl;

import com.example.demo.entity.Priority;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public SkillGapRecommendation getRecommendation() {
        SkillGapRecommendation rec = new SkillGapRecommendation();
        rec.setSkillName("Spring Boot");
        rec.setPriority(Priority.HIGH);
        rec.setRecommendation("Focus on REST APIs, JPA, and Exception Handling");
        return rec;
    }
}
