package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "skill_gap_recommendations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillGapRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;

    @ManyToOne(optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(nullable = false)
    private String recommendedAction;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)
    private Double gapScore;

    @Column(nullable = false)
    private String generatedBy;

    @Column(nullable = false)
    private Instant generatedAt;

    @PrePersist
    public void assignDefaults() {

        if (gapScore != null) {
            if (gapScore >= 20) priority = "HIGH";
            else if (gapScore >= 10) priority = "MEDIUM";
            else priority = "LOW";
        }

        this.generatedAt = Instant.now();

        if (generatedBy == null) {
            generatedBy = "SYSTEM";
        }
    }
}
