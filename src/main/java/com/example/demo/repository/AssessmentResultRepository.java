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

    // Fixes error in AssessmentServiceImpl.java
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    // Fixes error in LargeIntegrationTestNGTest.java
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.userId = :userId " +
           "AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(
            @Param("userId") Long userId, 
            @Param("start") Instant start, 
            @Param("end") Instant end);

    // Required for t030 and t031 in tests
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.studentProfile.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.userId = :userId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(@Param("userId") Long userId);

    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long profileId, Long skillId);
}