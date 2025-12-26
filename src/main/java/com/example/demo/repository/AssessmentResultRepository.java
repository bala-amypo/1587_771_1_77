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
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentId, Long skillId);

    // HQL for t058: Aggregation by cohort
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    // HQL for t060: Sorting and recent results
    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :studentId ORDER BY a.attemptedAt DESC")
    List<AssessmentResult> findRecentByStudent(@Param("studentId") Long studentId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.studentProfile.id = :studentId AND a.attemptedAt BETWEEN :from AND :to")
    List<AssessmentResult> findResultsForStudentBetween(@Param("studentId") Long studentId, 
                                                       @Param("from") Instant from, 
                                                       @Param("to") Instant to);
}