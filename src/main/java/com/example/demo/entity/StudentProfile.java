// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;
// import java.time.Instant;

// @Entity
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// public class StudentProfile {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     private String enrollmentId;
//     private String grade;  // Required for test t009
//     private String cohort; // Required for tests t030, t058

//     @OneToOne
//     @JoinColumn(name = "user_id")
//     private User user;

//     private Instant lastUpdatedAt;

//     @PrePersist
//     @PreUpdate
//     public void preUpdate() {
//         // Required for tests t015 and t044
//         this.lastUpdatedAt = Instant.now();
//     }
// }
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "student_profile")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Required for t021
    private String enrollmentId;

    private String grade;
    private Long userId;
    private Instant lastUpdatedAt;

    // Fix for t015 and t044: Automatic timestamping
    @PrePersist
    @PreUpdate
    public void preUpdate() {
        this.lastUpdatedAt = Instant.now();
    }
}