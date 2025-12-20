package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillGapRecommendationRepository extends JpaRepository<SkillGapRecommendation, Long> {
    
    List<SkillGapRecommendation> findByUserId(Long userId);
    
    List<SkillGapRecommendation> findBySkillId(Long skillId);
    
    List<SkillGapRecommendation> findByUserIdAndSkillId(Long userId, Long skillId);
    
    List<SkillGapRecommendation> findByPriorityLevel(String priorityLevel);
    
    List<SkillGapRecommendation> findByUserIdOrderByPriorityLevelAsc(Long userId);
    
    @Query("SELECT sgr FROM SkillGapRecommendation sgr WHERE sgr.userId = :userId AND sgr.priorityLevel = 'HIGH'")
    List<SkillGapRecommendation> findHighPriorityRecommendationsByUser(@Param("userId") Long userId);
    
    @Query("SELECT sgr FROM SkillGapRecommendation sgr WHERE sgr.priorityLevel IN ('HIGH', 'MEDIUM') ORDER BY sgr.createdDate DESC")
    List<SkillGapRecommendation> findActiveRecommendations();
}