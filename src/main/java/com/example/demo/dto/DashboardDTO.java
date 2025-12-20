package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private StudentProfileDTO studentProfile;
    
    private Integer totalSkillGaps;
    
    private Integer criticalSkillGaps;
    
    private Integer completedAssessments;
    
    private Integer pendingRecommendations;
    
    private Integer completedRecommendations;
    
    private Double overallProgress;
    
    private List<SkillGapRecordDTO> recentSkillGaps;
    
    private List<SkillGapRecommendationDTO> topRecommendations;
    
    private List<AssessmentResultDTO> recentAssessments;
}