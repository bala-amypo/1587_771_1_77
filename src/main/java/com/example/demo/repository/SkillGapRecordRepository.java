package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface SkillGapRecordRepository extends JpaRepository<SkillGapRecord, Long> {

    List<SkillGapRecord> findByStudentProfileId(Long studentProfileId);
    @Modifying
    @Transactional
    @Query("DELETE FROM SkillGapRecord s WHERE s.studentProfile.id = :studentProfileId")
    void deleteByStudentProfileId(Long studentProfileId);
}
