package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*; // Fixes "package javax.persistence does not exist"
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

    // Added to fix "cannot find symbol: method grade(java.lang.String)"
    private String grade;

    private String cohort;

    private Instant lastUpdatedAt;

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        this.lastUpdatedAt = Instant.now();
    }
}