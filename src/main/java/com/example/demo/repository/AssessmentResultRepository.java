package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    
    // Supports t007, t051
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);
    
    // Critical: Supports t024, t025, t040, t045
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentId, Long skillId);

    // Supports t031 (JPQL for ordering)
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :studentId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(@Param("studentId") Long studentId);

    // Supports t030, t047, t058 (Aggregation)
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    // Supports t054 (Time-series)
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :id AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(@Param("id") Long id, @Param("start") Instant start, @Param("end") Instant end);
}