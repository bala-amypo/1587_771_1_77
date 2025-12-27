
// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.Instant;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class StudentProfile {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @OneToOne
//     private User user;

//     @Column(unique = true)
//     private String enrollmentId;

//     private String grade;

//     private Integer yearLevel;

//     private Boolean active = true;

//     @Builder.Default
//     private Instant lastUpdatedAt = Instant.now();

//     @PreUpdate
//     public void preUpdate() {
//         lastUpdatedAt = Instant.now();
//     }
// }






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

    @OneToOne(optional = false) // Requirement: required
    private User user;

    @Column(unique = true, nullable = false) // Requirement: unique, required
    private String enrollmentId;

    @Column(nullable = false) // Requirement: cohort added and required
    private String cohort;

    private String grade;

    @Column(nullable = false) // Requirement: required
    private Integer yearLevel;

    @Builder.Default
    private Boolean active = true;

    @Builder.Default
    private Instant lastUpdatedAt = Instant.now();

    @PrePersist // Added to set timestamp on initial creation
    public void prePersist() {
        if (lastUpdatedAt == null) {
            lastUpdatedAt = Instant.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdatedAt = Instant.now();
    }
}