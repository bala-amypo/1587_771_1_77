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

    // Fixes "cannot find symbol: method findByStudentIdAndSkillId"
    List<AssessmentResult> findByStudentIdAndSkillId(Long studentId, Long skillId);

    // Fixes "cannot find symbol: method findByStudentProfileIdAndSkillId"
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);

    // Fixes missing method for getResultsByStudent
    List<AssessmentResult> findByStudentId(Long studentId);

    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.student.id = :studentId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(@Param("studentId") Long studentId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.student.id = :studentId AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(@Param("studentId") Long studentId, @Param("start") Instant start, @Param("end") Instant end);
}