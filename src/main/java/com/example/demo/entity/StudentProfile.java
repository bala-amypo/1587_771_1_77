package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String enrollmentId;
    private String grade;  // Required for test t009
    private String cohort; // Required for tests t030, t058

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Instant lastUpdatedAt;

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        // Required for tests t015 and t044
        this.lastUpdatedAt = Instant.now();
    }
}