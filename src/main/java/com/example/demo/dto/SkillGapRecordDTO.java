package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapRecordDTO {
    private Long id;
    
    @NotNull(message = "Student ID is required")
    private Long studentId;
    
    @NotNull(message = "Skill ID is required")
    private Long skillId;
    
    private String skillName;
    
    private Integer currentLevel;
    
    private Integer requiredLevel;
    
    private Integer gapSize;
    
    private String severity;
    
    private String status;
    
    private String actionPlan;
    
    private LocalDateTime identifiedDate;
    
    private LocalDateTime targetDate;
    
    private Integer progress;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}