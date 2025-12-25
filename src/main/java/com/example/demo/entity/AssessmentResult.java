package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "assessment_results")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String assessmentId;
    
    @Column(nullable = false)
    private Double score;
    
    @Column(nullable = false)
    @Builder.Default
    private Double maxScore = 100.0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_profile_id")
    private StudentProfile studentProfile;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;
    
    @Column(name = "cohort_id")
    private String cohortId;
    
    @Column(nullable = false, updatable = false)
    @Builder.Default
    private Instant attemptedAt = Instant.now();
}
