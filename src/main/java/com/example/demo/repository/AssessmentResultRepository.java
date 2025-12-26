package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    
    // This exact signature is required to fix the error at [31,42] 
    // in AssessmentServiceImpl.java
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    // Required for the service getRecentResults method
    List<AssessmentResult> findRecentByStudent(Long userId);
}