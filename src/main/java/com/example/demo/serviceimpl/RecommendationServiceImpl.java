package com.example.demo.serviceimpl;

import com.example.demo.entity.Priority;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public List<SkillGapRecommendation> getRecommendationsByStudent(Long studentId) {

        // Dummy data for now
        List<SkillGapRecommendation> list = new ArrayList<>();

        SkillGapRecommendation rec = new SkillGapRecommendation();
        rec.setSkillName("Spring Boot");
        rec.setPriority(Priority.HIGH);
        rec.setRecommendation("Improve REST APIs, JPA, and Exception Handling");

        list.add(rec);

        return list;
    }
}
