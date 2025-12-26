// src/main/java/com/example/demo/entity/User.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role { STUDENT, INSTRUCTOR, ADMIN }
}