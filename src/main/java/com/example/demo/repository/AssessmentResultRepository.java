package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    // Add this method to resolve the "cannot find symbol" error in tests
    List<AssessmentResult> findResultsForStudentBetween(Long userId, Instant start, Instant end);
    
    // Ensure these existing methods used in your tests are also present
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);
    List<AssessmentResult> findRecentByStudent(Long userId);
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long profileId, Long skillId);
}