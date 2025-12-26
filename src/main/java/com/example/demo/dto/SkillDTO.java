package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillDTO {
    private Long id;
    private String code; // Or skillName per requirements
    private String category;
    private String description;
    private Double minCompetencyScore;
    private boolean active;
}