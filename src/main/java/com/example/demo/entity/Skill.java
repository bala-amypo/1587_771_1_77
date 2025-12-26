package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String code;
    
    private String name;
    
    private String category; // Needed for SkillServiceImpl
    
    private Double minCompetencyScore; // Needed for SkillGapServiceImpl

    @Builder.Default
    private boolean active = true;
}