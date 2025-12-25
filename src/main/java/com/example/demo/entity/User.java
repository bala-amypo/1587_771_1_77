package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class User {

    public enum Role { ADMIN, INSTRUCTOR, STUDENT }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        if (role == null) {
            role = Role.STUDENT;
        }
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    // ---------- Getters & Setters ----------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    // ---------- Builder ----------

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final User u = new User();

        public Builder id(Long id) { u.setId(id); return this; }
        public Builder fullName(String n) { u.setFullName(n); return this; }
        public Builder email(String e) { u.setEmail(e); return this; }
        public Builder password(String p) { u.setPassword(p); return this; }
        public Builder role(Role r) { u.setRole(r); return this; }

        public User build() { return u; }
    }
}
