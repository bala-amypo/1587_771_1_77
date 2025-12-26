package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "skill_gap_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillGapRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    private Double currentScore;
    private Double targetScore;
    private Double gapScore;

    @Builder.Default
    private Instant calculatedAt = Instant.now();
}