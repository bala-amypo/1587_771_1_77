

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code; // or skillName

    @Column(nullable = false)
    private String category;

    private String description;

    @Column(nullable = false)
    private Double minCompetencyScore; // Range 0-100

    @Builder.Default
    private Boolean active = true;
}

