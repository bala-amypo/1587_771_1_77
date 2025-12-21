package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "skill_gap_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gap_id")
    private Long gapId;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "skill_id", nullable = false)
    private Long skillId;
    
    @Column(name = "current_level", nullable = false)
    private Integer currentLevel;
    
    @Column(name = "target_level", nullable = false)
    private Integer targetLevel;
    
    @Column(name = "gap_level")
    private Integer gapLevel;
    
    @Column(name = "identified_at")
    private LocalDateTime identifiedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    private Skill skill;
    
    @PrePersist
    protected void onCreate() {
        identifiedAt = LocalDateTime.now();
        if (gapLevel == null && currentLevel != null && targetLevel != null) {
            gapLevel = targetLevel - currentLevel;
        }
    }
}
