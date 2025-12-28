
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @Column(unique = true)
    private String enrollmentId;

    // REQUIRED BY TESTS
    private String grade;

    private Integer yearLevel;

    private Boolean active = true;

    // ✅ MUST NOT BE NULL (required by TestNG)
    @Builder.Default
    private Instant lastUpdatedAt = Instant.now();

    @PreUpdate
    public void preUpdate() {
        lastUpdatedAt = Instant.now();
    }
}
