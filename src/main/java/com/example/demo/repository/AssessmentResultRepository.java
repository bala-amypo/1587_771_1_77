package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    
    List<AssessmentResult> findByUserId(Long userId);
    
    List<AssessmentResult> findBySkillId(Long skillId);
    
    Optional<AssessmentResult> findByUserIdAndSkillId(Long userId, Long skillId);
    
    List<AssessmentResult> findByScoreGreaterThanEqual(Integer score);
    
    List<AssessmentResult> findByScoreLessThan(Integer score);
    
    List<AssessmentResult> findByUserIdOrderByAssessmentDateDesc(Long userId);
    
    @Query("SELECT ar FROM AssessmentResult ar WHERE ar.userId = :userId AND ar.assessmentDate >= :startDate")
    List<AssessmentResult> findRecentAssessmentsByUser(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT ar FROM AssessmentResult ar WHERE ar.score >= :minScore AND ar.score <= :maxScore")
    List<AssessmentResult> findByScoreRange(@Param("minScore") Integer minScore, @Param("maxScore") Integer maxScore);
}