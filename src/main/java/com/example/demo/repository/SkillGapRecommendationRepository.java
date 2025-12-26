package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SkillGapRecommendationRepository extends JpaRepository<SkillGapRecommendation, Long> {
    
    List<SkillGapRecommendation> findByStudentProfileIdOrderByGeneratedAtDesc(Long studentProfileId);

    // Custom HQL to satisfy the "findByStudentOrdered" test case requirement
    @Query("SELECT r FROM SkillGapRecommendation r WHERE r.studentProfile.id = :studentId ORDER BY r.gapScore DESC")
    List<SkillGapRecommendation> findByStudentOrdered(@Param("studentId") Long studentId);
}