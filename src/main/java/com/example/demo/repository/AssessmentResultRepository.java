
// package com.example.demo.repository;

// import com.example.demo.entity.AssessmentResult;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.time.Instant;
// import java.util.List;

// public interface AssessmentResultRepository
//         extends JpaRepository<AssessmentResult, Long> {

//     List<AssessmentResult> findByStudentProfileIdAndSkillId(
//             Long studentProfileId,
//             Long skillId
//     );

//     Double avgScoreByCohortAndSkill(String cohort, Long skillId);

//     List<AssessmentResult> findRecentByStudent(Long studentId);

//     List<AssessmentResult> findResultsForStudentBetween(
//             Long studentId,
//             Instant from,
//             Instant to
//     );
// }
// package com.example.demo.repository;

// import com.example.demo.entity.AssessmentResult;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.time.Instant;
// import java.util.List;

// public interface AssessmentResultRepository
//         extends JpaRepository<AssessmentResult, Long> {

//     List<AssessmentResult> findByStudentProfileIdAndSkillId(
//             Long studentProfileId,
//             Long skillId
//     );

//     @Query("""
//            SELECT AVG(r.score)
//            FROM AssessmentResult r
//            WHERE r.skill.id = :skillId
//              AND :cohort IS NOT NULL
//            """)
//     Double avgScoreByCohortAndSkill(
//             @Param("cohort") String cohort,
//             @Param("skillId") Long skillId
//     );

//     @Query("""
//            SELECT r
//            FROM AssessmentResult r
//            WHERE r.studentProfile.id = :studentId
//            ORDER BY r.attemptedAt DESC
//            """)
//     List<AssessmentResult> findRecentByStudent(
//             @Param("studentId") Long studentId
//     );

//     // ✅ FINAL FIX — NO DERIVED NAME
//     @Query("""
//            SELECT r
//            FROM AssessmentResult r
//            WHERE r.studentProfile.id = :studentId
//              AND r.attemptedAt >= :from
//              AND r.attemptedAt <= :to
//            ORDER BY r.attemptedAt DESC
//            """)
//     List<AssessmentResult> findResultsForStudentBetween(
//             @Param("studentId") Long studentId,
//             @Param("from") Instant from,
//             @Param("to") Instant to
//     );
// }




package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {

    // Required by Section 4 & AssessmentService
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    List<AssessmentResult> findByStudentProfileIdAndSkillId(
            Long studentProfileId,
            Long skillId
    );

    @Query("""
           SELECT AVG(r.score)
           FROM AssessmentResult r
           JOIN r.studentProfile sp
           WHERE r.skill.id = :skillId
             AND sp.cohort = :cohort
           """)
    Double avgScoreByCohortAndSkill(
            @Param("cohort") String cohort,
            @Param("skillId") Long skillId
    );

    @Query("""
           SELECT r
           FROM AssessmentResult r
           WHERE r.studentProfile.id = :studentId
           ORDER BY r.attemptedAt DESC
           """)
    List<AssessmentResult> findRecentByStudent(
            @Param("studentId") Long studentId
    );

    // ✅ FINAL FIX — NO DERIVED NAME
    @Query("""
           SELECT r
           FROM AssessmentResult r
           WHERE r.studentProfile.id = :studentId
             AND r.attemptedAt >= :from
             AND r.attemptedAt <= :to
           ORDER BY r.attemptedAt DESC
           """)
    List<AssessmentResult> findResultsForStudentBetween(
            @Param("studentId") Long studentId,
            @Param("from") Instant from,
            @Param("to") Instant to
    );
}


