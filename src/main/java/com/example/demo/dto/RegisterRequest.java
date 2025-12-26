package com.example.demo.dto;

import com.example.demo.entity.User.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private Role role; // Defaults handled in service/entity
}