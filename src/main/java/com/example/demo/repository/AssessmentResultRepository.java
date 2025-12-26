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
    
    // Required for Service Implementation and Tests
    List<AssessmentResult> findByStudentId(Long studentId);
    List<AssessmentResult> findByStudentIdAndSkillId(Long studentId, Long skillId);
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long profileId, Long skillId);

    // Required for t030, t047, t058: Average score by cohort
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.studentProfile.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    // Required for t031: Recent results by student
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.user.id = :studentId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(@Param("studentId") Long studentId);

    // Required for t054: Time series query
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.user.id = :studentId AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(@Param("studentId") Long studentId, @Param("start") Instant start, @Param("end") Instant end);
}