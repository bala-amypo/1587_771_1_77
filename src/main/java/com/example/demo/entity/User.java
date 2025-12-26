package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    public enum Role { STUDENT, INSTRUCTOR, ADMIN }
}