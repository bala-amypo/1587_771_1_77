package com.skillgap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentDTO {
    private Long id;
    
    @NotNull(message = "Student ID is required")
    private Long studentId;
    
    @NotNull(message = "Skill ID is required")
    private Long skillId;
    
    private String skillName;
    
    @NotNull(message = "Score is required")
    private Integer score;
    
    private Integer maxScore;
    
    private String assessmentType;
    
    private String status;
    
    private LocalDateTime assessmentDate;
    
    private String feedback;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}