package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileDTO {
    private Long id;
    
    @NotBlank(message = "Full name is required")
    private String fullName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    private String studentId;
    
    private String department;
    
    private String major;
    
    private String year;
    
    private String phoneNumber;
    
    private LocalDate dateOfBirth;
    
    private String address;
    
    private String careerGoals;
    
    private String interests;
    
    private String previousExperience;
    
    private Double gpa;
    
    private String profilePictureUrl;
    
    private Boolean isActive;
    
    private Long userId;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}