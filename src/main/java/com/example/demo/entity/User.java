
// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;
// import java.time.Instant;

// @Entity
// @Getter @Setter
// @NoArgsConstructor @AllArgsConstructor @Builder
// public class User {

//     public enum Role { ADMIN, INSTRUCTOR, STUDENT }

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String fullName;

//     @Column(unique = true, nullable = false)
//     private String email;

//     private String password;

//     @Enumerated(EnumType.STRING)
//     private Role role = Role.STUDENT;

//     private Instant createdAt = Instant.now();
// }
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "users") // ✅ FIXED
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String fullName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    private Instant createdAt = Instant.now();

    public enum Role {
        ADMIN,
        INSTRUCTOR,
        STUDENT
    }
}
