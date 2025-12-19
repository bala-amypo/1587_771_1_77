package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;

    private int minCompetencyScore;

    private boolean active;

    // 🔹 No-arg constructor
    public Skill() {
    }

    // 🔹 Getters & Setters

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

    public int getMinCompetencyScore() {
        return minCompetencyScore;
    }

    public void setMinCompetencyScore(int minCompetencyScore) {
        this.minCompetencyScore = minCompetencyScore;
    }

    public boolean isActive() {          // ✅ important for Spring Data
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
