package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "skill_gap_recommendations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillGapRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    private String recommendedAction;
    
    private String priority; // HIGH, MEDIUM, LOW

    private Double gapScore;

    private String generatedBy;

    @Builder.Default
    private Instant generatedAt = Instant.now();
}