package com.example.demo.serviceimpl;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.service.SkillGapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    @Autowired
    private SkillGapRecordRepository repository;

    @Override
    public SkillGapRecord saveSkillGap(SkillGapRecord record) {
        return repository.save(record);
    }

    @Override
    public List<SkillGapRecord> getSkillGapsByStudentId(Long studentId) {
        return repository.findByStudentId(studentId);
    }
}
