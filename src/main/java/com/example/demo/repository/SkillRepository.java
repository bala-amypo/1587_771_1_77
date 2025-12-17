package com.example.demo.repository;

import com.example.demo.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    // Used in SkillGapService and RecommendationService
    List<Skill> findByActiveTrue();
}