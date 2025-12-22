package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillGapRecommendationRepository
        extends JpaRepository<SkillGapRecommendation, Long> {
}
