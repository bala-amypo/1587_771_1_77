package com.example.demo.serviceimpl;

import com.example.demo.dto.SkillGapRecommendationDTO;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    
    private final SkillGapRecommendationRepository recommendationRepository;
    
    @Override
    @Transactional
    public SkillGapRecommendationDTO createRecommendation(SkillGapRecommendationDTO recommendationDTO) {
        SkillGapRecommendation recommendation = mapToEntity(recommendationDTO);
        SkillGapRecommendation savedRecommendation = recommendationRepository.save(recommendation);
        return mapToDTO(savedRecommendation);
    }
    
    @Override
    public SkillGapRecommendationDTO getRecommendationById(Long id) {
        SkillGapRecommendation recommendation = recommendationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found with id: " + id));
        return mapToDTO(recommendation);
    }
    
    @Override
    public List<SkillGapRecommendationDTO> getAllRecommendations() {
        return recommendationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<SkillGapRecommendationDTO> getRecommendationsByUserId(Long userId) {
        return recommendationRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<SkillGapRecommendationDTO> getRecommendationsByPriority(String priority) {
        return recommendationRepository.findByPriorityLevel(priority).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public SkillGapRecommendationDTO updateRecommendation(Long id, SkillGapRecommendationDTO recommendationDTO) {
        SkillGapRecommendation recommendation = recommendationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found with id: " + id));
        
        recommendation.setUserId(recommendationDTO.getUserId());
        recommendation.setSkillId(recommendationDTO.getSkillId());
        recommendation.setRecommendation(recommendationDTO.getRecommendation());
        recommendation.setPriorityLevel(recommendationDTO.getPriorityLevel());
        recommendation.setLearningResources(recommendationDTO.getLearningResources());
        
        SkillGapRecommendation updatedRecommendation = recommendationRepository.save(recommendation);
        return mapToDTO(updatedRecommendation);
    }
    
    @Override
    @Transactional
    public void deleteRecommendation(Long id) {
        if (!recommendationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recommendation not found with id: " + id);
        }
        recommendationRepository.deleteById(id);
    }
    
    private SkillGapRecommendationDTO mapToDTO(SkillGapRecommendation recommendation) {
        return new SkillGapRecommendationDTO(
                recommendation.getId(),
                recommendation.getUserId(),
                recommendation.getSkillId(),
                recommendation.getRecommendation(),
                recommendation.getPriorityLevel(),
                recommendation.getLearningResources()
        );
    }
    
    private SkillGapRecommendation mapToEntity(SkillGapRecommendationDTO dto) {
        return new SkillGapRecommendation(
                dto.getId(),
                dto.getUserId(),
                dto.getSkillId(),
                dto.getRecommendation(),
                dto.getPriorityLevel(),
                dto.getLearningResources(),
                null
        );
    }
}