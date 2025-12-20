package com.example.demo.repository;

import com.example.demo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    
    Optional<StudentProfile> findByUserId(Long userId);
    
    List<StudentProfile> findByCurrentSemester(Integer semester);
    
    List<StudentProfile> findByDepartment(String department);
    
    List<StudentProfile> findByCareerGoal(String careerGoal);
    
    boolean existsByUserId(Long userId);
}
