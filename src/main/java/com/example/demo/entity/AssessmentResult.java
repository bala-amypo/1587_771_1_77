package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 👇 TC EXPECTS THIS NAME
    private Double scoreObtained;

    private Double maxScore;
    private Instant attemptedAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Skill skill;
}
