package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

    // Required for getResultsByStudent implementation
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    // Required for getResultsByStudentAndSkill implementation
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);

    // FIX: Added @Query for the HQL test requirement (t030)
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.studentProfile.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    // Required for t031
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :id ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(@Param("id") Long studentProfileId);

    // Required for t054
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :id AND a.attemptedAt BETWEEN :from AND :to")
    List<AssessmentResult> findResultsForStudentBetween(
            @Param("id") Long studentProfileId,
            @Param("from") Instant from,
            @Param("to") Instant to
    );
}