package com.example.demo.service;

import com.example.demo.dto.SkillGapRecordDTO;
import java.util.List;

public interface SkillGapService {
    SkillGapRecordDTO createSkillGapRecord(SkillGapRecordDTO recordDTO);
    SkillGapRecordDTO getSkillGapRecordById(Long id);
    List<SkillGapRecordDTO> getAllSkillGapRecords();
    List<SkillGapRecordDTO> getSkillGapRecordsByUserId(Long userId);
    List<SkillGapRecordDTO> getSkillGapRecordsBySkillId(Long skillId);
    SkillGapRecordDTO updateSkillGapRecord(Long id, SkillGapRecordDTO recordDTO);
    void deleteSkillGapRecord(Long id);
}