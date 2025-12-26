package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String assessmentId;
    private Double score;
    @Builder.Default
    private Double maxScore = 100.0;
    @Builder.Default
    private Instant attemptedAt = Instant.now();

    @ManyToOne
    private StudentProfile studentProfile;
    
    @ManyToOne
    private Skill skill;
}