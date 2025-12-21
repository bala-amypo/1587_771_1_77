package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileDTO {
    private Long profileId;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    private String department;
    private Integer semester;
    private String interests;
    private String careerGoals;
}