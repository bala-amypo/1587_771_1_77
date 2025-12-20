package com.example.demo.service;

import com.example.demo.entity.SkillGapRecord;

import java.util.List;

public interface SkillGapService {

    SkillGapRecord saveSkillGap(SkillGapRecord record);

    List<SkillGapRecord> getSkillGapsByStudentId(Long studentId);
}
