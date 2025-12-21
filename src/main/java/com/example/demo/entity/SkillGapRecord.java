package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SkillGapRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    @ManyToOne
    private Skill skill;

    private Double currentScore;
    private Double targetScore;
    private Double gapScore;
    private LocalDateTime calculatedAt;

    @PrePersist
    public void onCalculate() {
        this.calculatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public StudentProfile getStudentProfile() { return studentProfile; }
    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }

    public Double getCurrentScore() { return currentScore; }
    public void setCurrentScore(Double currentScore) {
        this.currentScore = currentScore;
    }

    public Double getTargetScore() { return targetScore; }
    public void setTargetScore(Double targetScore) {
        this.targetScore = targetScore;
    }

    public Double getGapScore() { return gapScore; }
    public void setGapScore(Double gapScore) {
        this.gapScore = gapScore;
    }

    public LocalDateTime getCalculatedAt() { return calculatedAt; }
}
