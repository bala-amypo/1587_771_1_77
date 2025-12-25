package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String enrollmentId;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<AssessmentResult> assessmentResults;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<SkillGapRecord> skillGapRecords;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<SkillGapRecommendation> recommendations;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(String enrollmentId) { this.enrollmentId = enrollmentId; }
}
