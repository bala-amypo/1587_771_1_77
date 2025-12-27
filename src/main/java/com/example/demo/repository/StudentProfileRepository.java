
// package com.example.demo.repository;

// import com.example.demo.entity.StudentProfile;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

//     Optional<StudentProfile> findByUserId(Long userId);

//     boolean existsByEnrollmentId(String enrollmentId);
// }





package com.example.demo.repository;

import com.example.demo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    Optional<StudentProfile> findByUserId(Long userId);

    boolean existsByEnrollmentId(String enrollmentId);
    
    // ADDED: Requirement for finding by enrollment ID
    Optional<StudentProfile> findByEnrollmentId(String enrollmentId);
}