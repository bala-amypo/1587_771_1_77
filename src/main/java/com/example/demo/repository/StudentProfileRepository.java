package com.example.demo.repository;

import com.example.demo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    // Required by test suite - exact signature
    Optional<StudentProfile> findByEnrollmentId(String enrollmentId);
}
