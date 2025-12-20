package com.skillgap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {
    private Long id;
    
    @NotBlank(message = "Skill name is required")
    private String name;
    
    private String description;
    
    private String category;
    
    private String subcategory;
    
    private String difficulty;
    
    private Integer requiredLevel;
    
    private Boolean isActive;
    
    private String keywords;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
