package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skill_gap_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentProfile student;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;
    
    @Column(name = "current_level")
    private Integer currentLevel = 0;
    
    @Column(name = "required_level")
    private Integer requiredLevel;
    
    @Column(name = "gap_size")
    private Integer gapSize;
    
    @Column(length = 20)
    private String severity;
    
    @Column(length = 20)
    private String status = "IDENTIFIED";
    
    @Column(name = "action_plan", columnDefinition = "TEXT")
    private String actionPlan;
    
    @Column(name = "identified_date")
    private LocalDateTime identifiedDate;
    
    @Column(name = "target_date")
    private LocalDateTime targetDate;
    
    @Column
    private Integer progress = 0;
    
    @OneToMany(mappedBy = "skillGap", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SkillGapRecommendation> recommendations = new HashSet<>();
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    @PreUpdate
    public void calculateGapSize() {
        if (requiredLevel != null && currentLevel != null) {
            this.gapSize = requiredLevel - currentLevel;
            
            if (gapSize >= 5) {
                this.severity = "CRITICAL";
            } else if (gapSize >= 3) {
                this.severity = "HIGH";
            } else if (gapSize >= 1) {
                this.severity = "MEDIUM";
            } else {
                this.severity = "LOW";
            }
        }
    }
}
