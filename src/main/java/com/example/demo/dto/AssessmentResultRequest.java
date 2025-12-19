package com.example.demo.dto;

public class AssessmentResultRequest {

    private String subject;
    private double scoreObtained;
    private Long studentId;
    private Long skillId;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScoreObtained() {
        return scoreObtained;
    }

    public void setScoreObtained(double scoreObtained) {
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
