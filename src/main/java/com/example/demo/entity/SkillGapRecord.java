package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class SkillGapRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String missingSkill;
    private int requiredLevel;

    public SkillGapRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMissingSkill() {
        return missingSkill;
    }

    public void setMissingSkill(String missingSkill) {
        this.missingSkill = missingSkill;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }
}
