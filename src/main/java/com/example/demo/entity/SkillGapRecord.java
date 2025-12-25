package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

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

    private Instant calculatedAt;

    /**
     * Rules:
     * gapScore = targetScore - currentScore (only if positive)
     * auto-generated timestamp
     */
    @PrePersist
    @PreUpdate
    public void computeGap() {

        if (currentScore == null) currentScore = 0.0;
        if (targetScore == null) targetScore = 0.0;

        double gap = targetScore - currentScore;

        if (gap < 0) {
            gap = 0.0;
        }

        this.gapScore = gap;

        if (calculatedAt == null) {
            calculatedAt = Instant.now();
        }
    }

    // ---------- Getters & Setters ----------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StudentProfile getStudentProfile() { return studentProfile; }
    public void setStudentProfile(StudentProfile studentProfile) { this.studentProfile = studentProfile; }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }

    public Double getCurrentScore() { return currentScore; }
    public void setCurrentScore(Double currentScore) { this.currentScore = currentScore; }

    public Double getTargetScore() { return targetScore; }
    public void setTargetScore(Double targetScore) { this.targetScore = targetScore; }

    public Double getGapScore() { return gapScore; }
    public void setGapScore(Double gapScore) { this.gapScore = gapScore; }

    public Instant getCalculatedAt() { return calculatedAt; }
    public void setCalculatedAt(Instant calculatedAt) { this.calculatedAt = calculatedAt; }

    