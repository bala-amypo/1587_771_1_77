package com.example.demo.serviceimpl;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository repository;

    public SkillGapServiceImpl(SkillGapRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public SkillGapRecord saveSkillGap(SkillGapRecord record) {
        return repository.save(record);
    }

    @Override
    public List<SkillGapRecord> getSkillGapsByStudent(Long studentId) {
        return repository.findByStudent_Id(studentId);
    }
}
