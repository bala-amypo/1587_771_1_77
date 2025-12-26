package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(
    name = "student_profiles",
    uniqueConstraints = @UniqueConstraint(columnNames = "enrollmentId")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String enrollmentId;

    @Column(nullable = false)
    private String cohort;

    @Column(nullable = false)
    private Integer yearLevel;

    @Column(nullable = false)
    private Boolean active;

    @Column
    private Instant lastUpdatedAt;

    @PrePersist
    public void prePersist() {
        if (active == null) active = true;
        lastUpdatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdatedAt = Instant.now();
    }
}
