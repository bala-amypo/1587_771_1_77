package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "assessment_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentResult {

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
    private String assessmentId;

    @Column(nullable = false)
    private Double scoreObtained;

    @Column(nullable = false)
    private Double maxScore;

    @Column(nullable = false)
    private Instant assessedAt;

    @PrePersist
    public void prePersist() {

        if (maxScore == null) {
            maxScore = 100.0;
        }

        if (scoreObtained == null ||
            scoreObtained < 0 || scoreObtained > maxScore) {
            throw new IllegalArgumentException(
                "Score must be between 0 and 100"
            );
        }

        this.assessedAt = Instant.now();
    }
}
