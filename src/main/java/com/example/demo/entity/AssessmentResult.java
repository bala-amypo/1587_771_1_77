package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    @ManyToOne
    private Skill skill;

    private Double scoreObtained;
    private Double maxScore;
    private LocalDateTime assessedAt;

    @PrePersist
    public void onAssess() {
        this.assessedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public StudentProfile getStudentProfile() { return studentProfile; }
    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }

    public Double getScoreObtained() { return scoreObtained; }
    public void setScoreObtained(Double scoreObtained) {
        this.scoreObtained = scoreObtained;
    }

    public Double getMaxScore() { return maxScore; }
    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public LocalDateTime getAssessedAt() { return assessedAt; }
}
