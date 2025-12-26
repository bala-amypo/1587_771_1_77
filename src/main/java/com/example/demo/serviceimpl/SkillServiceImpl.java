package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public Skill createSkill(Skill skill) {
        if (skillRepository.findByCode(skill.getCode()).isPresent()) {
            throw new IllegalArgumentException("Skill code must be unique");
        }
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill skillDetails) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
        
        // Correct field mapping for CRUD operations [cite: 63]
        skill.setName(skillDetails.getName());
        skill.setCode(skillDetails.getCode());
        skill.setCategory(skillDetails.getCategory()); 
        skill.setActive(skillDetails.isActive());
        
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getActiveSkills() {
        return skillRepository.findByActiveTrue(); [cite: 64, 65]
    }

    @Override
    public Skill getById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }
}