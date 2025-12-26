package com.example.demo.dto;

import lombok.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfileDTO {
    private Long id;
    private Long userId;
    private String enrollmentId;
    private String cohort;
    private Integer yearLevel;
    private boolean active;
    private Instant lastUpdatedAt;
}