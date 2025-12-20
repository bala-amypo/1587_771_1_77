package com.example.demo.service;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.SkillGapRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔥 THIS IS MANDATORY
public class SkillGapService {

    @Autowired
    private SkillGapRecordRepository skillGapRecordRepository;

    public SkillGapRecord saveSkillGap(SkillGapRecord record) {
        return skillGapRecordRepository.save(record);
    }

    public List<SkillGapRecord> getSkillGapsByStudentId(Long studentId) {
        return skillGapRecordRepository.findByStudentId(studentId);
    }
}
