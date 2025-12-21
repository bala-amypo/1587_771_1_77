package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SkillGapRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    @ManyToOne
    private Skill skill;

    private String recommendedAction;
    private String priority;
    private Double gapScore;
    private String generatedBy;
    private LocalDateTime generatedAt;

    @PrePersist
    public void onGenerate() {
        this.generatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public StudentProfile getStudentProfile() { return studentProfile; }
    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }

    public String getRecommendedAction() { return recommendedAction; }
    public void setRecommendedAction(String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }

    public String getPriority() { return priority; }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Double getGapScore() { return gapScore; }
    public void setGapScore(Double gapScore) {
        this.gapScore = gapScore;
    }

    public String getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
}
