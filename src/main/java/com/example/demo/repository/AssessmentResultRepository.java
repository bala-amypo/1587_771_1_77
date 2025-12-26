package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    // Methods required by AssessmentServiceImpl
    List<AssessmentResult> findByStudentId(Long studentId);
    List<AssessmentResult> findByStudentIdAndSkillId(Long studentId, Long skillId);

    // Methods required by your Integration Test (t030, t031, t040, t054)
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.studentProfile.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.user.id = :studentId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(Long studentId);

    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long profileId, Long skillId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.user.id = :studentId AND a.attemptedAt BETWEEN :start AND :end")
    List<AssessmentResult> findResultsForStudentBetween(Long studentId, Instant start, Instant end);
}