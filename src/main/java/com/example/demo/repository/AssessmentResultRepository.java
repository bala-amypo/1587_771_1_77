package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

    // Fixes compilation error in AssessmentServiceImpl
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    // Fixes compilation error in LargeIntegrationTestNGTest
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.userId = :userId " +
           "AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(Long userId, Instant start, Instant end);
    
    // Add these for other tests in your LargeIntegrationTest file
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);
    List<AssessmentResult> findRecentByStudent(Long userId);
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long profileId, Long skillId);
}