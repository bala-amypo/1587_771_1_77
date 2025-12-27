
// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.Instant;

// @Entity
// @Table(name = "users") // ✅ FIXED
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, unique = true)
//     private String email;

//     private String fullName;

//     private String password;

//     @Enumerated(EnumType.STRING)
//     private Role role;

//     @Builder.Default
//     private Instant createdAt = Instant.now();

//     public enum Role {
//         ADMIN,
//         INSTRUCTOR,
//         STUDENT
//     }
// }





package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Requirement: required and unique
    private String email;

    @Column(nullable = false) // Requirement: required
    private String fullName;

    @Column(nullable = false) // Requirement: required
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    @Column(nullable = false, updatable = false) // Requirement: auto-generated on create
    private Instant createdAt = Instant.now();

    public enum Role {
        ADMIN,
        INSTRUCTOR,
        STUDENT
    }

    /**
     * Rules Enforcement:
     * 1. Role defaults to STUDENT if not specified.
     * 2. Ensures createdAt is populated if builder is not used.
     */
    @PrePersist
    protected void onCreate() {
        if (this.role == null) {
            this.role = Role.STUDENT;
        }
        if (this.createdAt == null) {
            this.createdAt = Instant.now();
        }
    }
}