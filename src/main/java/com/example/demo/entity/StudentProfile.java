package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enrollmentId;

    private String grade;

    private Instant lastUpdatedAt;

    @PrePersist
    public void prePersist() {
        if (lastUpdatedAt == null) {
            lastUpdatedAt = Instant.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdatedAt = Instant.now();
    }

    // ---------- Getters & Setters ----------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(String enrollmentId) { this.enrollmentId = enrollmentId; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public Instant getLastUpdatedAt() { return lastUpdatedAt; }
    public void setLastUpdatedAt(Instant lastUpdatedAt) { this.lastUpdatedAt = lastUpdatedAt; }

    // ---------- Builder ----------

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final StudentProfile p = new StudentProfile();

        public Builder id(Long id) { p.setId(id); return this; }
        public Builder enrollmentId(String e) { p.setEnrollmentId(e); return this; }
        public Builder grade(String g) { p.setGrade(g); return this; }
        public Builder lastUpdatedAt(Instant t) { p.setLastUpdatedAt(t); return this; }

        public StudentProfile build() { return p; }
    }
}
