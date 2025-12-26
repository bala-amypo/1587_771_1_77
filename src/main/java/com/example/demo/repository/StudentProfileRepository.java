package com.example.demo.repository;

import com.example.demo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    
    // Required for profileService.getByUserId() in tests
    Optional<StudentProfile> findByUserId(Long userId);

    // Required for t021 and t037 in LargeIntegrationTestNGTest
    boolean existsByEnrollmentId(String enrollmentId);
}