package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {
    private Long skillId;
    
    @NotBlank(message = "Skill name is required")
    private String name;
    
    private String category;
    private String description;
    private String difficultyLevel;
}