package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

    // Fixes AssessmentServiceImpl compilation error
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    // Fixes LargeIntegrationTestNGTest lines 644-645
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.userId = :userId " +
           "AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(Long userId, Instant start, Instant end);
    
    // Required for HQL tests (t030, t031)
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.studentProfile.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.userId = :userId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(Long userId);

    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long profileId, Long skillId);
}