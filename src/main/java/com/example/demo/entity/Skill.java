package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Skill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Double minCompetencyScore;
    @Builder.Default
    private boolean active = true; // Required for t016 [cite: 1, 47]
}