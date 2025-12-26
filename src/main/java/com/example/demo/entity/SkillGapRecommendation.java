package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillGapRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double gapScore; // Must match 'gapScore' in HQL
    
    private String recommendationText;
    private String generatedBy;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentProfile studentProfile; // Must match 'studentProfile' in HQL
}