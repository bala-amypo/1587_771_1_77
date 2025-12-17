package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface SkillGapRecommendationRepository extends JpaRepository<SkillGapRecommendation, Long> {
    List<SkillGapRecommendation> findByStudentProfileIdOrderByGeneratedAtDesc(Long studentProfileId);
    @Modifying
    @Transactional
    @Query("DELETE FROM SkillGapRecommendation s WHERE s.studentProfile.id = :studentProfileId")
    void deleteByStudentProfileId(Long studentProfileId);
}
