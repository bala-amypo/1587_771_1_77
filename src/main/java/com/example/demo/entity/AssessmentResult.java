package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AssessmentResult {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String assessmentId;
    private Double score;
    @Builder.Default
    private Double maxScore = 100.0; // Required for t017 [cite: 1, 48]
    private String cohort;
    @Builder.Default
    private Instant attemptedAt = Instant.now(); // Required for t050 [cite: 1, 109]

    @ManyToOne
    private StudentProfile studentProfile;
    @ManyToOne
    private Skill skill;
}