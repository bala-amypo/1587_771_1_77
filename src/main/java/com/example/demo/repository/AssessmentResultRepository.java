package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

    // Supports getResultsByStudent
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    // Supports getResultsByStudentAndSkill
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);

    // Required for Test t030
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.studentProfile.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    // Required for Test t031
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :id ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(@Param("id") Long studentProfileId);

    // Required for Test t054
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :id AND a.attemptedAt BETWEEN :from AND :to")
    List<AssessmentResult> findResultsForStudentBetween(
            @Param("id") Long studentProfileId, 
            @Param("from") Instant from, 
            @Param("to") Instant to
    );
}