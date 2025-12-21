package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapRecommendationDTO {
    private Long recommendationId;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Skill ID is required")
    private Long skillId;
    
    @NotBlank(message = "Recommendation text is required")
    private String recommendationText;
    
    private String priorityLevel;
    private String resources;
    private String createdAt;
}