
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

    @ManyToOne
    private StudentProfile studentProfile;

    @ManyToOne
    private Skill skill;

    private String recommendedAction;
    private String priority;
    private Double gapScore;
    private String generatedBy;

    private Instant generatedAt = Instant.now();
}
