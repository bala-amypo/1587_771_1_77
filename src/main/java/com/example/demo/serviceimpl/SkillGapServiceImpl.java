
package com.example.demo.serviceimpl;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository recordRepository;

    public SkillGapServiceImpl(SkillGapRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<SkillGapRecord> analyzeSkillGaps(Long studentId) {
        // TODO: add gap logic later
        return recordRepository.findByStudentProfileId(studentId);
    }

    @Override
    public List<SkillGapRecord> getSkillGapsForStudent(Long studentId) {
        return recordRepository.findByStudentProfileId(studentId);
    }
}