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
    
    // Test t008/t041 look at these specifically
    private Double score;

    @Builder.Default
    private Double maxScore = 100.0; // Required for t017

    @ManyToOne
    @JoinColumn(name = "student_profile_id")
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Builder.Default
    private Instant attemptedAt = Instant.now(); // Required for t050
}