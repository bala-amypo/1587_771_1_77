package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    
    // Add this to fix the "cannot find symbol" error
    List<AssessmentResult> findByStudentIdAndSkillId(Long studentId, Long skillId);

    // Add this if you need to support the "getResultsByStudent" method
    List<AssessmentResult> findByStudentId(Long studentId);
}