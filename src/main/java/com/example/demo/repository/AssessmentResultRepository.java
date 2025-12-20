package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {
}
