package com.example.demo.serviceimpl;

import com.example.demo.dto.SkillDTO;
import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public SkillDTO createSkill(SkillDTO skillDTO) {
        // Check if skill with same name already exists
        if (skillRepository.existsByName(skillDTO.getName())) {
            throw new RuntimeException("Skill with name '" + skillDTO.getName() + "' already exists");
        }

        Skill skill = new Skill();
        skill.setName(skillDTO.getName());
        skill.setCategory(skillDTO.getCategory());
        skill.setDescription(skillDTO.getDescription());
        skill.setDifficultyLevel(skillDTO.getDifficultyLevel());

        Skill savedSkill = skillRepository.save(skill);
        return convertToDTO(savedSkill);
    }

    @Override
    public SkillDTO getSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
        return convertToDTO(skill);
    }

    @Override
    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SkillDTO updateSkill(Long id, SkillDTO skillDTO) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));

        // Check if updating name conflicts with existing skill
        if (!skill.getName().equals(skillDTO.getName()) && 
            skillRepository.existsByName(skillDTO.getName())) {
            throw new RuntimeException("Skill with name '" + skillDTO.getName() + "' already exists");
        }

        skill.setName(skillDTO.getName());
        skill.setCategory(skillDTO.getCategory());
        skill.setDescription(skillDTO.getDescription());
        skill.setDifficultyLevel(skillDTO.getDifficultyLevel());

        Skill updatedSkill = skillRepository.save(skill);
        return convertToDTO(updatedSkill);
    }

    @Override
    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new RuntimeException("Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
    }

    @Override
    public List<SkillDTO> getSkillsByCategory(String category) {
        return skillRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCategories() {
        return skillRepository.findDistinctCategories();
    }

    private SkillDTO convertToDTO(Skill skill) {
        SkillDTO dto = new SkillDTO();
        dto.setSkillId(skill.getSkillId());
        dto.setName(skill.getName());
        dto.setCategory(skill.getCategory());
        dto.setDescription(skill.getDescription());
        dto.setDifficultyLevel(skill.getDifficultyLevel());
        return dto;
    }
}