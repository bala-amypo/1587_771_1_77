package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column(name = "recommendation_id")
    private Long recommendationId;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "skill_id", nullable = false)
    private Long skillId;
    
    @Column(name = "recommendation_text", nullable = false, columnDefinition = "TEXT")
    private String recommendationText;
    
    @Column(name = "priority_level", length = 20)
    private String priorityLevel;
    
    @Column(columnDefinition = "TEXT")
    private String resources;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    private Skill skill;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
