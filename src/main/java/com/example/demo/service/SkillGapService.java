package com.example.demo.service;

import com.example.demo.entity.SkillGapRecord;

import java.util.List;

public interface SkillGapService {

    SkillGapRecord computeGap(Long studentId, Long skillId);

    List<SkillGapRecord> computeAllGaps(Long studentId);

    List<SkillGapRecord> getGapHistory(Long studentId);
}
