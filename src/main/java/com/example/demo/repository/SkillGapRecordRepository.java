package com.example.demo.repository;

import com.example.demo.entity.SkillGapRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillGapRecordRepository
        extends JpaRepository<SkillGapRecord, Long> {

    List<SkillGapRecord> findByStudentId(Long studentId);
}
