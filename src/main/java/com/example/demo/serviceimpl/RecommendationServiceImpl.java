package com.example.demo.serviceimpl;

import com.example.demo.dto.RecommendationDTO;
import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final SkillGapRecommendationRepository repository;

    public RecommendationServiceImpl(SkillGapRecommendationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RecommendationDTO> getRecommendationsByStudent(Long studentId) {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecommendationDTO createRecommendation(RecommendationDTO dto) {
        SkillGapRecommendation entity = new SkillGapRecommendation();
        entity.setSkillName(dto.getSkillName());
        entity.setPriority(dto.getPriority());
        entity.setRecommendation(dto.getRecommendation());

        SkillGapRecommendation saved = repository.save(entity);
        return mapToDTO(saved);
    }

    private RecommendationDTO mapToDTO(SkillGapRecommendation entity) {
        RecommendationDTO dto = new RecommendationDTO();
        dto.setSkillName(entity.getSkillName());
        dto.setPriority(entity.getPriority());
        dto.setRecommendation(entity.getRecommendation());
        return dto;
    }
}
