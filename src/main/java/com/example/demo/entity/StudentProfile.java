package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*; // Use jakarta for Spring Boot 3+, javax for 2.x
import java.time.Instant;

@Entity
@Table(name = "student_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "enrollment_id", unique = true)
    private String enrollmentId;

    // Add this field to fix the StudentProfileBuilder error in tests
    private String grade;

    private String cohort;

    private Instant lastUpdatedAt;

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        this.lastUpdatedAt = Instant.now();
    }
}