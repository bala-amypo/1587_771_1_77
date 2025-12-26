package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    @ManyToOne
    private Skill skill;

    private Double requiredScore;
    private Double actualScore;
    private Double gap;
    private Instant calculatedAt;

    // Custom constructor needed for the stream logic in ServiceImpl
    public SkillGapRecord(Skill skill, Double gap) {
        this.skill = skill;
        this.gap = gap;
        this.requiredScore = skill.getMinCompetencyScore();
        this.calculatedAt = Instant.now();
    }
}