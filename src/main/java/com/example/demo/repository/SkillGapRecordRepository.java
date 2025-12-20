package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillGapRecordRepository extends JpaRepository<SkillGapRecord, Long> {
    
    List<SkillGapRecord> findByUserId(Long userId);
    
    List<SkillGapRecord> findBySkillId(Long skillId);
    
    Optional<SkillGapRecord> findByUserIdAndSkillId(Long userId, Long skillId);
    
    List<SkillGapRecord> findByGapLevelGreaterThanEqual(Integer gapLevel);
    
    List<SkillGapRecord> findByUserIdOrderByGapLevelDesc(Long userId);
    
    @Query("SELECT sgr FROM SkillGapRecord sgr WHERE sgr.userId = :userId AND sgr.gapLevel > 0 ORDER BY sgr.gapLevel DESC")
    List<SkillGapRecord> findSignificantGapsByUser(@Param("userId") Long userId);
    
    @Query("SELECT sgr FROM SkillGapRecord sgr WHERE sgr.gapLevel >= :minGap AND sgr.gapLevel <= :maxGap")
    List<SkillGapRecord> findByGapLevelRange(@Param("minGap") Integer minGap, @Param("maxGap") Integer maxGap);
}