package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    
    List<AssessmentResult> findByStudentProfileId(Long studentId);
    
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentId, Long skillId);

    /**
     * Requirement for t058: 
     * Uses Wrapper 'Double' instead of 'double' to handle null cases when no records exist.
     */
    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    /**
     * Requirement for t060:
     * HQL query to find students who scored above a specific threshold for a specific skill.
     */
    @Query("SELECT a FROM AssessmentResult a WHERE a.skill.id = :skillId AND a.score >= :minScore")
    List<AssessmentResult> findHighPerformers(@Param("skillId") Long skillId, @Param("minScore") Double minScore);
}