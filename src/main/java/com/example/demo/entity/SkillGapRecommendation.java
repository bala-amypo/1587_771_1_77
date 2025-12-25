package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class SkillGapRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile student;

    @ManyToOne
    private Skill skill;

    private Double gapScore;

    private String generatedBy;

    private Instant generatedAt;

    @PrePersist
    public void prePersist() {
        if (generatedAt == null) {
            generatedAt = Instant.now();
        }
    }

    // ---------- Getters & Setters ----------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StudentProfile getStudent() { return student; }
    public void setStudent(StudentProfile student) { this.student = student; }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }

    public Double getGapScore() { return gapScore; }
    public void setGapScore(Double gapScore) { this.gapScore = gapScore; }

    public String getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(String generatedBy) { this.generatedBy = generatedBy; }

    public Instant getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(Instant generatedAt) { this.generatedAt = generatedAt; }

    // ---------- Builder ----------

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final SkillGapRecommendation r = new SkillGapRecommendation();

        public Builder id(Long id) { r.setId(id); return this; }
        public Builder student(StudentProfile s) { r.setStudent(s); return this; }
        public Builder skill(Skill sk) { r.setSkill(sk); return this; }
        public Builder gapScore(Double g) { r.setGapScore(g); return this; }
        public Builder generatedBy(String gb) { r.setGeneratedBy(gb); return this; }
        public Builder generatedAt(Instant t) { r.setGeneratedAt(t); return this; }

        public SkillGapRecommendation build() { return r; }
    }
}
