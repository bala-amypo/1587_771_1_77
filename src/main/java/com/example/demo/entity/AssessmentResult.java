
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

    @ManyToOne
    private StudentProfile studentProfile;

    @ManyToOne
    private Skill skill;

    private String assessmentId;

    private Double score;

    // ✅ REQUIRED BY TESTS
    @Builder.Default
    private Double maxScore = 100.0;

    // ✅ REQUIRED BY TESTS
    @Builder.Default
    private Instant attemptedAt = Instant.now();
}
