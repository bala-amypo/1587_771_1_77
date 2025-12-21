package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentResultDTO {
    private Long resultId;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Skill ID is required")
    private Long skillId;
    
    @NotNull(message = "Score is required")
    @Min(0)
    @Max(100)
    private Integer score;
    
    private String feedback;
    private String assessedAt;
}