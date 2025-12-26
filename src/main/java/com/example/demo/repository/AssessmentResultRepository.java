package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    
    // Fixes error in AssessmentServiceImpl
    List<AssessmentResult> findByStudentProfileId(Long studentId);

    [cite_start]List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentId, Long skillId); [cite: 198, 214]

    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.studentProfile.cohort = ?1 AND a.skill.id = ?2")
    [cite_start]Double avgScoreByCohortAndSkill(String cohort, Long skillId); [cite: 199, 229]

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = ?1 ORDER BY a.attemptedAt DESC")
    [cite_start]List<AssessmentResult> findRecentByStudent(Long studentId); [cite: 200, 201]

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = ?1 AND a.attemptedAt BETWEEN ?2 AND ?3")
    [cite_start]List<AssessmentResult> findResultsForStudentBetween(Long id, Instant start, Instant end); [cite: 239]
}