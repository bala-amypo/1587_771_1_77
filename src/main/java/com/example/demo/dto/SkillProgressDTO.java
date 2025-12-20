package com.skillgap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillProgressDTO {
    private Long skillId;
    
    private String skillName;
    
    private Integer initialLevel;
    
    private Integer currentLevel;
    
    private Integer targetLevel;
    
    private Integer progressPercentage;
    
    private LocalDateTime startDate;
    
    private LocalDateTime lastUpdated;
    
    private String status;
}
