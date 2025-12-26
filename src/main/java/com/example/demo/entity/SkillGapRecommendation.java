package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
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

    @Column(nullable = false)
    private String recommendedAction;

    private String priority; // HIGH, MEDIUM, LOW

    @Column(nullable = false)
    private Double gapScore;

    private String generatedBy;

    private Instant generatedAt;

    @PrePersist
    protected void onCreate() {
        generatedAt = Instant.now();
        // Logic often handled in service, but can be set here if score is present
        if (gapScore != null && priority == null) {
            this.priority = gapScore >= 20 ? "HIGH" : (gapScore >= 10 ? "MEDIUM" : "LOW");
        }
    }
}