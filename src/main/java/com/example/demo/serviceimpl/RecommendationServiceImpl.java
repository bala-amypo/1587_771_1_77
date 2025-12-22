package com.example.demo.serviceimpl;

import com.example.demo.entity.Priority;
import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceImpl {

    public SkillGapRecommendation buildRecommendation() {
        SkillGapRecommendation rec = new SkillGapRecommendation();
        rec.setSkillName("Spring Boot");
        rec.setPriority(Priority.HIGH);
        rec.setRecommendation("Focus on REST APIs and JPA");
        return rec;
    }
}
