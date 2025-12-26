// src/main/java/com/example/demo/repository/AssessmentResultRepository.java
package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentId, Long skillId);

    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.studentProfile.cohort = ?1 AND a.skill.id = ?2")
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = ?1 ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(Long studentId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = ?1 AND a.attemptedAt BETWEEN ?2 AND ?3")
    List<AssessmentResult> findResultsForStudentBetween(Long id, Instant start, Instant end);
}