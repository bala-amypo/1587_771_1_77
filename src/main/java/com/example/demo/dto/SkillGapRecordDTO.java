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
public class SkillGapRecordDTO {
    private Long gapId;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Skill ID is required")
    private Long skillId;
    
    @NotNull(message = "Current level is required")
    @Min(0)
    @Max(100)
    private Integer currentLevel;
    
    @NotNull(message = "Target level is required")
    @Min(0)
    @Max(100)
    private Integer targetLevel;
    
    private Integer gapLevel;
    private String identifiedAt;
}