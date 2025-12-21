package com.example.demo.serviceimpl;

import com.example.demo.dto.AssessmentResultDTO;
import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    @Autowired
    private AssessmentResultRepository assessmentResultRepository;

    @Override
    public AssessmentResultDTO createAssessment(AssessmentResultDTO resultDTO) {
        AssessmentResult result = new AssessmentResult();
        result.setUserId(resultDTO.getUserId());
        result.setSkillId(resultDTO.getSkillId());
        result.setScore(resultDTO.getScore());
        result.setFeedback(resultDTO.getFeedback());
        result.setAssessedAt(LocalDateTime.now());

        AssessmentResult saved = assessmentResultRepository.save(result);
        return convertToDTO(saved);
    }

    @Override
    public AssessmentResultDTO getAssessmentById(Long id) {
        AssessmentResult result = assessmentResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found with id: " + id));
        return convertToDTO(result);
    }

    @Override
    public List<AssessmentResultDTO> getAllAssessments() {
        return assessmentResultRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssessmentResultDTO> getAssessmentsByStudentId(Long userId) {
        return assessmentResultRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssessmentResultDTO> getAssessmentsBySkillId(Long skillId) {
        return assessmentResultRepository.findBySkillId(skillId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AssessmentResultDTO updateAssessment(Long id, AssessmentResultDTO resultDTO) {
        AssessmentResult result = assessmentResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found with id: " + id));

        result.setScore(resultDTO.getScore());
        result.setFeedback(resultDTO.getFeedback());

        AssessmentResult updated = assessmentResultRepository.save(result);
        return convertToDTO(updated);
    }

    @Override
    public void deleteAssessment(Long id) {
        if (!assessmentResultRepository.existsById(id)) {
            throw new RuntimeException("Assessment not found with id: " + id);
        }
        assessmentResultRepository.deleteById(id);
    }

    private AssessmentResultDTO convertToDTO(AssessmentResult result) {
        AssessmentResultDTO dto = new AssessmentResultDTO();
        dto.setResultId(result.getResultId());
        dto.setUserId(result.getUserId());
        dto.setSkillId(result.getSkillId());
        dto.setScore(result.getScore());
        dto.setFeedback(result.getFeedback());
        dto.setAssessedAt(result.getAssessedAt() != null ? result.getAssessedAt().toString() : null);
        return dto;
    }
}