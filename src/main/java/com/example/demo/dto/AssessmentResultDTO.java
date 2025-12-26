package com.example.demo.dto;

import lombok.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentResultDTO {
    private Long id;
    private Long studentProfileId;
    private Long skillId;
    private String assessmentId;
    private Double score;
    private Double maxScore;
    private Instant attemptedAt;
}