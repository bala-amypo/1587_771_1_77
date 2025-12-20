package com.skillgap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "skill_gap_recommendations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapRecommendation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentProfile student;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_gap_id", nullable = false)
    private SkillGapRecord skillGap;
    
    @Column(name = "recommendation_type", length = 50)
    private String recommendationType;
    
    @Column(nullable = false, length = 200)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "resource_url", length = 500)
    private String resourceUrl;
    
    @Column(name = "resource_type", length = 50)
    private String resourceType;
    
    @Column(name = "estimated_duration")
    private Integer estimatedDuration;
    
    @Column(length = 20)
    private String difficulty;
    
    @Column
    private Integer priority = 1;
    
    @Column(length = 20)
    private String status = "PENDING";
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}