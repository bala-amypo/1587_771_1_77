package com.example.demo.serviceimpl;

import com.example.demo.dto.SkillGapRecordDTO;
import com.example.demo.entity.SkillGapRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.service.SkillGapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillGapServiceImpl implements SkillGapService {
    
    private final SkillGapRecordRepository skillGapRecordRepository;
    
    @Override
    @Transactional
    public SkillGapRecordDTO createSkillGapRecord(SkillGapRecordDTO recordDTO) {
        int gapLevel = recordDTO.getRequiredLevel() - recordDTO.getCurrentLevel();
        recordDTO.setGapLevel(gapLevel);
        
        SkillGapRecord record = mapToEntity(recordDTO);
        SkillGapRecord savedRecord = skillGapRecordRepository.save(record);
        return mapToDTO(savedRecord);
    }
    
    @Override
    public SkillGapRecordDTO getSkillGapRecordById(Long id) {
        SkillGapRecord record = skillGapRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill gap record not found with id: " + id));
        return mapToDTO(record);
    }
    
    @Override
    public List<SkillGapRecordDTO> getAllSkillGapRecords() {
        return skillGapRecordRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<SkillGapRecordDTO> getSkillGapRecordsByUserId(Long userId) {
        return skillGapRecordRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<SkillGapRecordDTO> getSkillGapRecordsBySkillId(Long skillId) {
        return skillGapRecordRepository.findBySkillId(skillId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public SkillGapRecordDTO updateSkillGapRecord(Long id, SkillGapRecordDTO recordDTO) {
        SkillGapRecord record = skillGapRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill gap record not found with id: " + id));
        
        int gapLevel = recordDTO.getRequiredLevel() - recordDTO.getCurrentLevel();
        
        record.setUserId(recordDTO.getUserId());
        record.setSkillId(recordDTO.getSkillId());
        record.setCurrentLevel(recordDTO.getCurrentLevel());
        record.setRequiredLevel(recordDTO.getRequiredLevel());
        record.setGapLevel(gapLevel);
        
        SkillGapRecord updatedRecord = skillGapRecordRepository.save(record);
        return mapToDTO(updatedRecord);
    }
    
    @Override
    @Transactional
    public void deleteSkillGapRecord(Long id) {
        if (!skillGapRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill gap record not found with id: " + id);
        }
        skillGapRecordRepository.deleteById(id);
    }
    
    private SkillGapRecordDTO mapToDTO(SkillGapRecord record) {
        return new SkillGapRecordDTO(
                record.getId(),
                record.getUserId(),
                record.getSkillId(),
                record.getCurrentLevel(),
                record.getRequiredLevel(),
                record.getGapLevel()
        );
    }
    
    private SkillGapRecord mapToEntity(SkillGapRecordDTO dto) {
        return new SkillGapRecord(
                dto.getId(),
                dto.getUserId(),
                dto.getSkillId(),
                dto.getCurrentLevel(),
                dto.getRequiredLevel(),
                dto.getGapLevel(),
                null
        );
    }
}