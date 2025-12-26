package com.example.demo.dto;

import lombok.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationDTO {
    private Long studentProfileId;
    private Long skillId;
    private String skillName;
    private Double gapScore;
    private String priority; // HIGH / MEDIUM / LOW
    private String recommendedAction;
    private Instant generatedAt;
}