package com.skillgap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    
    private String tokenType = "Bearer";
    
    private Long userId;
    
    private String email;
    
    private String fullName;
    
    private String role;
    
    private Long expiresIn;
}
