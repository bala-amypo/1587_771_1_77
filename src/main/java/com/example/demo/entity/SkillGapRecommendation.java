package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "skill_gap_recommendations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapRecommendation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;
    
    @Column(nullable = false)
    private Double gapScore;
    
    @Column(nullable = false)
    private String generatedBy;
    
    @Column(nullable = false, updatable = false)
    @Builder.Default
    private Instant generatedAt = Instant.now();
}
