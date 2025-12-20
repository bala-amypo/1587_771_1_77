package com.example.demo.serviceimpl;

import com.example.demo.dto.SkillDTO;
import com.example.demo.entity.Skill;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    
    private final SkillRepository skillRepository;
    
    @Override
    @Transactional
    public SkillDTO createSkill(SkillDTO skillDTO) {
        if (skillRepository.existsBySkillName(skillDTO.getSkillName())) {
            throw new DuplicateResourceException("Skill already exists: " + skillDTO.getSkillName());
        }
        
        Skill skill = mapToEntity(skillDTO);
        Skill savedSkill = skillRepository.save(skill);
        return mapToDTO(savedSkill);
    }
    
    @Override
    public SkillDTO getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        return mapToDTO(skill);
    }
    
    @Override
    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public SkillDTO updateSkill(Long id, SkillDTO skillDTO) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
        
        if (!skill.getSkillName().equals(skillDTO.getSkillName()) && 
            skillRepository.existsBySkillName(skillDTO.getSkillName())) {
            throw new DuplicateResourceException("Skill already exists: " + skillDTO.getSkillName());
        }
        
        skill.setSkillName(skillDTO.getSkillName());
        skill.setDescription(skillDTO.getDescription());
        skill.setCategory(skillDTO.getCategory());
        skill.setDifficultyLevel(skillDTO.getDifficultyLevel());
        
        Skill updatedSkill = skillRepository.save(skill);
        return mapToDTO(updatedSkill);
    }
    
    @Override
    @Transactional
    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
    }
    
    @Override
    public List<SkillDTO> getSkillsByCategory(String category) {
        return skillRepository.findByCategory(category).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<SkillDTO> searchSkillsByName(String name) {
        return skillRepository.findBySkillNameContainingIgnoreCase(name).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    private SkillDTO mapToDTO(Skill skill) {
        return new SkillDTO(
                skill.getId(),
                skill.getSkillName(),
                skill.getDescription(),
                skill.getCategory(),
                skill.getDifficultyLevel()
        );
    }
    
    private Skill mapToEntity(SkillDTO dto) {
        return new Skill(
                dto.getId(),
                dto.getSkillName(),
                dto.getDescription(),
                dto.getCategory(),
                dto.getDifficultyLevel()
        );
    }
}
