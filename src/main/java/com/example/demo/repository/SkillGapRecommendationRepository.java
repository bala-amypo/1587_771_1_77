package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SkillGapRecommendationRepository extends JpaRepository<SkillGapRecommendation, Long> {

    // Fix: Use HQL to join with student profile and order by gap score
    @Query("SELECT r FROM SkillGapRecommendation r WHERE r.studentProfile.id = :studentId ORDER BY r.gapScore DESC")
    List<SkillGapRecommendation> findByStudentOrdered(@Param("studentId") Long studentId);
}