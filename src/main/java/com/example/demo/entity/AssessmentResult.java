package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "assessment_results")
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private double scoreObtained;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    public AssessmentResult() {}

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public double getScoreObtained() {
        return scoreObtained;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setScoreObtained(double scoreObtained) {
        this.scoreObtained = scoreObtained;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
