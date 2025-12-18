package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private double scoreObtained;

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

    public double getScoreObtained() {
        return scoreObtained;
    }

    public void setScoreObtained(double scoreObtained) {
        this.scoreObtained = scoreObtained;
    }
}
