package com.example.demo.repository;

import com.example.demo.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    boolean existsByName(String name);
    List<Skill> findByCategory(String category);
    
    @Query("SELECT DISTINCT s.category FROM Skill s WHERE s.category IS NOT NULL")
    List<String> findDistinctCategories();
}
