package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code; // Often referred to as skillName in spec

    @Column(nullable = false)
    private String category;

    private String description;

    private Double minCompetencyScore;

    @Builder.Default
    private boolean active = true;
}