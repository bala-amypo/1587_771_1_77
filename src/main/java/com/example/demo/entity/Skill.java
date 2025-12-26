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
    private boolean active;
}