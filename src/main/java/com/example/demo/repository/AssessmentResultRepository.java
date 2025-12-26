package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    
    // Fixes error in AssessmentServiceImpl.java
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    // Fixes error in LargeIntegrationTestNGTest.java
    List<AssessmentResult> findResultsForStudentBetween(Long userId, Instant start, Instant end);

    // Ensure these methods used in your test file also exist
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);
    List<AssessmentResult> findRecentByStudent(Long userId);
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long profileId, Long skillId);
}