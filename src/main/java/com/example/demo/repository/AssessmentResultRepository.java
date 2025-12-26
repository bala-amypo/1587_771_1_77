package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    
    // Fix for compilation error [cite: 9]
    List<AssessmentResult> findByStudentProfileId(Long studentId);
    
    // Required for t024, t040, t045 [cite: 58, 89, 97]
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentId, Long skillId);

    // Required for t030, t047, t058 [cite: 74, 104, 120]
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    // Required for t031 [cite: 75]
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :studentId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(@Param("studentId") Long studentId);

    // Required for t054 [cite: 114]
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :studentId AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(@Param("studentId") Long studentId, @Param("start") Instant start, @Param("end") Instant end);
}