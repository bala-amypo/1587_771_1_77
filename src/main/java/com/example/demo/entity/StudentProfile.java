
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
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(unique = true, nullable = false)
    private String enrollmentId;

    @Column(nullable = false)
    private String cohort;

    @Column(nullable = false)
    private Integer yearLevel;

    @Builder.Default
    private Boolean active = true;

    private Instant lastUpdatedAt;

    @PrePersist
    protected void onCreate() {
        lastUpdatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdatedAt = Instant.now();
    }
}