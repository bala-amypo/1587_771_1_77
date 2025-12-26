package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentId, Long skillId);

    @Query("SELECT AVG(a.score) FROM AssessmentResult a WHERE a.cohort = :cohort AND a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(@Param("cohort") String cohort, @Param("skillId") Long skillId);

    @Query("SELECT a FROM AssessmentResult a WHERE a.skill.id = :skillId AND a.score >= :minScore")
    List<AssessmentResult> findHighPerformers(@Param("skillId") Long skillId, @Param("minScore") Double minScore);
}