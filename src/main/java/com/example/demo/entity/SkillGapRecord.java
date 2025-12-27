// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;
// import java.time.Instant;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class SkillGapRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private StudentProfile studentProfile;

//     @ManyToOne
//     private Skill skill;

//     private Double currentScore;
//     private Double targetScore;
//     private Double gapScore;

//     private Instant calculatedAt = Instant.now();
// }









package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillGapRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // Requirement: required
    private StudentProfile studentProfile;

    @ManyToOne(optional = false) // Requirement: required
    private Skill skill;

    @Column(nullable = false) // Requirement: required
    private Double currentScore;

    @Column(nullable = false) // Requirement: required
    private Double targetScore;

    @Column(nullable = false) // Requirement: required
    private Double gapScore;

    @Builder.Default
    @Column(nullable = false, updatable = false) // Requirement: auto-generated/required
    private Instant calculatedAt = Instant.now();

    /**
     * Rules Enforcement:
     * 1. gapScore = targetScore - currentScore
     * 2. auto-populates calculatedAt if missing
     */
    @PrePersist
    @PreUpdate
    public void calculateGap() {
        if (this.targetScore != null && this.currentScore != null) {
            // Rule: gapScore = targetScore - currentScore
            this.gapScore = this.targetScore - this.currentScore;
        }
        
        if (this.calculatedAt == null) {
            this.calculatedAt = Instant.now();
        }
    }
}