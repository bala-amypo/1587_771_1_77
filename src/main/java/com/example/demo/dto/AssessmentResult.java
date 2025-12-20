package com.example.demo.dto;

public class AssessmentResult {

    private Long id;
    private String subject;
    private int scoreObtained;
    private Long studentId;
    private Long skillId;

    public AssessmentResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getScoreObtained() {
        return scoreObtained;
    }

    public void setScoreObtained(int scoreObtained) {
        this.scoreObtained = scoreObtained;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}
