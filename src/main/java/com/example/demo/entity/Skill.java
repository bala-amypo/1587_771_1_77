package com.example.demo.entity;
import jakarta.persistence.*;
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skillName;
    private double minCompetencyScore;
    private boolean active;
    public Skill() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSkillName() {
        return skillName;
    }
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    public double getMinCompetencyScore() {
        return minCompetencyScore;
    }
    public void setMinCompetencyScore(double minCompetencyScore) {
        this.minCompetencyScore = minCompetencyScore;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
