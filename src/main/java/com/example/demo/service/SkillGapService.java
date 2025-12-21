package com.example.demo.service;

import com.example.demo.dto.SkillGapRecordDTO;
import java.util.List;

public interface SkillGapService {
    SkillGapRecordDTO createSkillGap(SkillGapRecordDTO recordDTO);
    SkillGapRecordDTO getSkillGapById(Long id);
    List<SkillGapRecordDTO> getAllSkillGaps();
    List<SkillGapRecordDTO> getSkillGapsByStudentId(Long userId);
    SkillGapRecordDTO updateSkillGap(Long id, SkillGapRecordDTO recordDTO);
    void deleteSkillGap(Long id);
    List<SkillGapRecordDTO> analyzeSkillGapsForStudent(Long userId);
}