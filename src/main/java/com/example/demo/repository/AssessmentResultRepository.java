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

    // Fixes "cannot find symbol" in LargeIntegrationTestNGTest
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.userId = :userId " +
           "AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(Long userId, Instant start, Instant end);

    // Supporting methods for HQL tests
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.studentProfile.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.userId = :userId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(Long userId);

    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long profileId, Long skillId);
}