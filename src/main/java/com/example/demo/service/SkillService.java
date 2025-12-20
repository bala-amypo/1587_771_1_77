package com.example.demo.service;

import com.example.demo.dto.SkillDTO;
import java.util.List;

public interface SkillService {
    SkillDTO createSkill(SkillDTO skillDTO);
    SkillDTO getSkillById(Long id);
    List<SkillDTO> getAllSkills();
    SkillDTO updateSkill(Long id, SkillDTO skillDTO);
    void deleteSkill(Long id);
    List<SkillDTO> getSkillsByCategory(String category);
    List<SkillDTO> searchSkillsByName(String name);
}