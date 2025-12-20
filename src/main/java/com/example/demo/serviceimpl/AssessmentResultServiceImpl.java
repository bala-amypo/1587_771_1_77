package com.example.demo.serviceimpl;

import com.example.demo.dto.AssessmentResultDTO;
import com.example.demo.entity.AssessmentResult;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssessmentResultServiceImpl implements AssessmentResultService {
    
    private final AssessmentResultRepository assessmentResultRepository;
    
    @Override
    @Transactional
    public AssessmentResultDTO createAssessmentResult(AssessmentResultDTO resultDTO) {
        AssessmentResult result = mapToEntity(resultDTO);
        AssessmentResult savedResult = assessmentResultRepository.save(result);
        return mapToDTO(savedResult);
    }
    
    @Override
    public AssessmentResultDTO getAssessmentResultById(Long id) {
        AssessmentResult result = assessmentResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment result not found with id: " + id));
        return mapToDTO(result);
    }
    
    @Override
    public List<AssessmentResultDTO> getAllAssessmentResults() {
        return assessmentResultRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<AssessmentResultDTO> getAssessmentResultsByUserId(Long userId) {
        return assessmentResultRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<AssessmentResultDTO> getAssessmentResultsBySkillId(Long skillId) {
        return assessmentResultRepository.findBySkillId(skillId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public AssessmentResultDTO updateAssessmentResult(Long id, AssessmentResultDTO resultDTO) {
        AssessmentResult result = assessmentResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment result not found with id: " + id));
        
        result.setUserId(resultDTO.getUserId());
        result.setSkillId(resultDTO.getSkillId());
        result.setScore(resultDTO.getScore());
        result.setMaxScore(resultDTO.getMaxScore());
        result.setFeedback(resultDTO.getFeedback());
        
        AssessmentResult updatedResult = assessmentResultRepository.save(result);
        return mapToDTO(updatedResult);
    }
    
    @Override
    @Transactional
    public void deleteAssessmentResult(Long id) {
        if (!assessmentResultRepository.existsById(id)) {
            throw new ResourceNotFoundException("Assessment result not found with id: " + id);
        }
        assessmentResultRepository.deleteById(id);
    }
    
    private AssessmentResultDTO mapToDTO(AssessmentResult result) {
        return new AssessmentResultDTO(
                result.getId(),
                result.getUserId(),
                result.getSkillId(),
                result.getScore(),
                result.getMaxScore(),
                result.getFeedback()
        );
    }
    
    private AssessmentResult mapToEntity(AssessmentResultDTO dto) {
        return new AssessmentResult(
                dto.getId(),
                dto.getUserId(),
                dto.getSkillId(),
                dto.getScore(),
                dto.getMaxScore(),
                null,
                dto.getFeedback()
        );
    }
}