package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SkillGapRecommendationRepository extends JpaRepository<SkillGapRecommendation, Long> {
    
    // Supports t038
    @Query("SELECT r FROM SkillGapRecommendation r WHERE r.studentProfile.id = :studentId ORDER BY r.generatedAt DESC")
    List<SkillGapRecommendation> findByStudentOrdered(@Param("studentId") Long studentId);
}