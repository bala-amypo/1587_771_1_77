package com.example.demo.dto;

import java.time.LocalDateTime;

public class RecommendationDTO {

    private String skillName;
    private String priority;
    private String recommendation;
    private LocalDateTime generatedAt;

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getPriority() { return priority; }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
