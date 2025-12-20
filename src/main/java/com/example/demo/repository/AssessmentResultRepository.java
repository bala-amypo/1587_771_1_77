package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {

    Optional<AssessmentResult> findByStudentProfile_IdAndSkill_Id(
            Long studentId,
            Long skillId
    );
}
