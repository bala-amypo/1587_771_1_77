package com.example.demo.service;

import com.example.demo.entity.Skill;
import java.util.List;

public interface SkillService {
    Skill createSkill(Skill skill);
    Skill getById(Long id);
    List<Skill> getActiveSkills();
}
