package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

    // REQUIRED by tests
    List<AssessmentResult> findByStudentProfileIdAndSkillId(
            Long studentProfileId,
            Long skillId
    );

    // Used in tests
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);

    List<AssessmentResult> findRecentByStudent(Long studentProfileId);

    List<AssessmentResult> findResultsForStudentBetween(
            Long studentProfileId,
            Instant from,
            Instant to
    );
}
