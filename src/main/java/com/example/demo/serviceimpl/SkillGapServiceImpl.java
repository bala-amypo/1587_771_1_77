package com.example.demo.serviceimpl;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.AssessmentResultRepository;
import java.util.Collections;
import java.util.List;

public class SkillGapServiceImpl {

    public SkillGapServiceImpl(
            SkillGapRecordRepository repo,
            SkillRepository skillRepo,
            AssessmentResultRepository assessmentRepo) {
    }

    public List<SkillGapRecord> computeGaps(Long studentProfileId) {
        return Collections.emptyList();
    }
}
