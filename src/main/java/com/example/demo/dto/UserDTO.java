package com.example.demo.dto;

import lombok.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private String role;
    private Instant createdAt;
}
