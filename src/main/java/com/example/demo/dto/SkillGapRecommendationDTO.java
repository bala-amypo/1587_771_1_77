package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapRecommendationDTO {
    private Long id;
    
    @NotNull(message = "Student ID is required")
    private Long studentId;
    
    @NotNull(message = "Skill gap ID is required")
    private Long skillGapId;
    
    private String skillName;
    
    private String recommendationType;
    
    private String title;
    
    private String description;
    
    private String resourceUrl;
    
    private String resourceType;
    
    private Integer estimatedDuration;
    
    private String difficulty;
    
    private Integer priority;
    
    private String status;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}

