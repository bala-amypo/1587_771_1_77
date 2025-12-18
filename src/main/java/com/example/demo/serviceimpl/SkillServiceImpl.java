package com.example.demo.service.impl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository repository;

    @Override
    public Skill addSkill(Skill skill) {
        return repository.save(skill);
    }

    @Override
    public Skill getSkillById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Skill> getAllSkills() {
        return repository.findAll();
    }

    @Override
    public Skill deactivateSkill(Long id) {
        Skill skill = repository.findById(id).orElseThrow();
        skill.setActive(false);
        return repository.save(skill);
    }
}
