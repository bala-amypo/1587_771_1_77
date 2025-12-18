package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {

    List<AssessmentResult> findByStudentProfileId(Long studentId);

    List<AssessmentResult> findByStudentProfileIdAndSkillId(
            Long studentId, Long skillId);
}
