
package com.example.demo.dto;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationDTO {
    private Long studentProfileId;
    private Long skillId;
    private Double gapScore;
    private String priority;
    private Instant generatedAt;
}
