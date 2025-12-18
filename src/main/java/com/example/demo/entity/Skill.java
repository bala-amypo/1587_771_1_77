package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name", unique = true)
    private String skillName;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "min_competency_score")
    private Double minCompetencyScore;

    @Column(name = "active")
    private Boolean active = true;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getMinCompetencyScore() { return minCompetencyScore; }
    public void setMinCompetencyScore(Double minCompetencyScore) { this.minCompetencyScore = minCompetencyScore; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}