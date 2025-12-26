package com.example.demo.dto;

import lombok.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillGapRecordDTO {
    private Long id;
    private Long studentProfileId;
    private Long skillId;
    private Double currentScore;
    private Double targetScore;
    private Double gapScore;
    private Instant calculatedAt;
}