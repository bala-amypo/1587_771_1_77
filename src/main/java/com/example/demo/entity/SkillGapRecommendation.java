
// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;
// import java.time.Instant;

// @Entity
// @Getter @Setter
// @NoArgsConstructor @AllArgsConstructor @Builder
// public class SkillGapRecommendation {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private StudentProfile studentProfile;

//     @ManyToOne
//     private Skill skill;

//     private String recommendedAction;
//     private String priority;
//     private Double gapScore;
//     private String generatedBy;

//     private Instant generatedAt = Instant.now();
// }






package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SkillGapRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // Requirement: required
    private StudentProfile studentProfile;

    @ManyToOne(optional = false) // Requirement: required
    private Skill skill;

    @Column(nullable = false) // Requirement: required
    private String recommendedAction;

    private String priority; // Rule: HIGH / MEDIUM / LOW based on gapScore

    @Column(nullable = false) // Requirement: required
    private Double gapScore;

    private String generatedBy;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private Instant generatedAt = Instant.now();

    /**
     * Rules Enforcement:
     * 1. Auto-populates generatedAt
     * 2. Sets Priority: gap >= 20 -> HIGH, else MEDIUM/LOW
     */
    @PrePersist
    protected void onCreate() {
        if (this.generatedAt == null) {
            this.generatedAt = Instant.now();
        }
        
        // Priority rule of thumb logic
        if (this.gapScore != null) {
            if (this.gapScore >= 20.0) {
                this.priority = "HIGH";
            } else if (this.gapScore >= 10.0) {
                this.priority = "MEDIUM";
            } else {
                this.priority = "LOW";
            }
        }
    }
}