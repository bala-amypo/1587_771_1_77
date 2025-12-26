package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(unique = true, nullable = false)
    private String enrollmentId;

    @Column(nullable = false)
    private String cohort;

    @Column(nullable = false)
    private Integer yearLevel;

    @Builder.Default
    private boolean active = true;

    private Instant lastUpdatedAt;

    @PrePersist
    protected void onCreate() {
        lastUpdatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdatedAt = Instant.now();
    }
}