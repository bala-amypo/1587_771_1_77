package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository repository;

    public SkillServiceImpl(SkillRepository repository) {
        this.repository = repository;
    }

    @Override
    public Skill createSkill(Skill skill) {
        if (repository.findByCode(skill.getCode()).isPresent()) {
            throw new IllegalArgumentException("unique keyword: Skill code must be unique");
        }
        if (skill.getMinCompetencyScore() < 0 || skill.getMinCompetencyScore() > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        return repository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("not found");
        skill.setId(id);
        return repository.save(skill);
    }

    @Override
    public Skill getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<Skill> getAllSkills() { return repository.findAll(); }

    @Override
    public List<Skill> getActiveSkills() { return repository.findByActiveTrue(); }

    @Override
    public void deactivateSkill(Long id) {
        Skill skill = getById(id);
        skill.setActive(false);
        repository.save(skill);
    }
}