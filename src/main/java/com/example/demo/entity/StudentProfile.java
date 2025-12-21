// --- StudentProfile.java ---
package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "student_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(length = 100)
    private String department;
    
    @Column
    private Integer semester;
    
    @Column(columnDefinition = "TEXT")
    private String interests;
    
    @Column(name = "career_goals", columnDefinition = "TEXT")
    private String careerGoals;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}