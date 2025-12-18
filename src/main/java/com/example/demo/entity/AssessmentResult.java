package com.example.demo.entity;
import jakarta.persistence.*;
@Entity
public class AssessmentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private double scoreObtained;
    @ManyToOne
    @JoinColumn(name = "student_profile_id")
    private StudentProfile studentProfile;
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    public AssessmentResult() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public double getScoreObtained() {
        return scoreObtained;
    }
    public void setScoreObtained(double scoreObtained) {
        this.scoreObtained = scoreObtained;
    }
    public StudentProfile getStudentProfile() {
        return studentProfile;
    }
    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }
    public Skill getSkill() {
        return skill;
    }
    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
