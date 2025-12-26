package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);
    
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentId, Long skillId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :studentId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(@Param("studentId") Long studentId);

    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :id AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(@Param("id") Long id, @Param("start") Instant start, @Param("end") Instant end);
}