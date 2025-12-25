package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;
    private String category;
    private String description;
    private Integer minCompetencyScore;
    private Boolean active;

    @OneToMany(mappedBy = "skill")
    private List<AssessmentResult> assessmentResults;

    @OneToMany(mappedBy = "skill")
    private List<SkillGapRecord> skillGapRecords;

    @OneToMany(mappedBy = "skill")
    private List<SkillGapRecommendation> recommendations;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getMinCompetencyScore() { return minCompetencyScore; }
    public void setMinCompetencyScore(Integer minCompetencyScore) {
        this.minCompetencyScore = minCompetencyScore;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public List<AssessmentResult> getAssessmentResults() { return assessmentResults; }
    public void setAssessmentResults(List<AssessmentResult> assessmentResults) {
        this.assessmentResults = assessmentResults;
    }

    public List<SkillGapRecord> getSkillGapRecords() { return skillGapRecords; }
    public void setSkillGapRecords(List<SkillGapRecord> skillGapRecords) {
        this.skillGapRecords = skillGapRecords;
    }

    public List<SkillGapRecommendation> getRecommendations() { return recommendations; }
    public void setRecommendations(List<SkillGapRecommendation> recommendations) {
        this.recommendations = recommendations;
    }
}
