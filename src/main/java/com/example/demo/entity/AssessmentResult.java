
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
// public class AssessmentResult {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private StudentProfile studentProfile;

//     @ManyToOne
//     private Skill skill;

//     private String assessmentId;

//     private Double score;

//     @Builder.Default
//     private Double maxScore = 100.0;

//     @Builder.Default
//     private Instant attemptedAt = Instant.now();
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
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // Required
    private StudentProfile studentProfile;

    @ManyToOne(optional = false) // Required
    private Skill skill;

    @Column(nullable = false) // Required
    private String assessmentId;

    // Mapping both 'score' and 'scoreObtained' concepts to one field
    @Column(nullable = false)
    private Double score;

    // ✅ REQUIRED BY TESTS
    @Builder.Default
    @Column(nullable = false)
    private Double maxScore = 100.0;

    // ✅ REQUIRED BY TESTS
    @Builder.Default
    @Column(nullable = false)
    private Instant attemptedAt = Instant.now();

    /**
     * Rules Enforcement:
     * scoreObtained ≥ 0 and ≤ maxScore; invalid → IllegalArgumentException 
     * with "Score must be between 0 and 100"
     */
    @PrePersist
    @PreUpdate
    public void validateScore() {
        if (this.maxScore == null) {
            this.maxScore = 100.0;
        }
        if (this.score == null || this.score < 0 || this.score > this.maxScore) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
    }
    
    // Alias getter for requirements mentioning 'scoreObtained'
    public Double getScoreObtained() {
        return this.score;
    }
}