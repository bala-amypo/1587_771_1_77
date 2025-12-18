package com.example.demo.service;
import com.example.demo.entity.Skill;
import java.util.List;
public interface SkillService {
    Skill addSkill(Skill skill);
    Skill getSkillById(Long id);
    List<Skill> getAllSkills();
    Skill deactivateSkill(Long id);
}
