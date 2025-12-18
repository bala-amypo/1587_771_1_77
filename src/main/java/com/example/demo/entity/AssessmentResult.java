package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "assessment_results")
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_profile_id")
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(name = "score_obtained")
    private Double scoreObtained;

    @Column(name = "max_score")
    private Double maxScore;

    @Column(name = "assessed_at")
    private Timestamp assessedAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public StudentProfile getStudentProfile() { return studentProfile; }
    public void setStudentProfile(StudentProfile studentProfile) { this.studentProfile = studentProfile; }
    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
    public Double getScoreObtained() { return scoreObtained; }
    public void setScoreObtained(Double scoreObtained) { this.scoreObtained = scoreObtained; }
    public Double getMaxScore() { return maxScore; }
    public void setMaxScore(Double maxScore) { this.maxScore = maxScore; }
    public Timestamp getAssessedAt() { return assessedAt; }
    public void setAssessedAt(Timestamp assessedAt) { this.assessedAt = assessedAt; }
}