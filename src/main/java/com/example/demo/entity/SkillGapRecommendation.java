package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class SkillGapRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String priority;
    private String recommendation;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentProfile student;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
