package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    
    // Required for t024, t025, t040, t045
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);

    // Required for t031 (JPQL for ordering)
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :studentId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(@Param("studentId") Long studentId);

    // Required for t030, t047, t058
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    // Required for t054
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :id AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(@Param("id") Long id, @Param("start") Instant start, @Param("end") Instant end);
}