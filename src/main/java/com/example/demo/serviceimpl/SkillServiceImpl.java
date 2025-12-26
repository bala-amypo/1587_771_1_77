package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;

import java.util.List;

public class SkillServiceImpl implements SkillService {

    private final SkillRepository repo;

    public SkillServiceImpl(SkillRepository repo) {
        this.repo = repo;
    }

    @Override
    public Skill createSkill(Skill skill) {

        if (skill.getMinCompetencyScore() < 0 || skill.getMinCompetencyScore() > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        repo.findBySkillName(skill.getSkillName())
                .ifPresent(s -> {
                    throw new IllegalArgumentException("Skill name already exists");
                });

        return repo.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill update) {

        Skill skill = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill not found"));

        skill.setSkillName(update.getSkillName());
        skill.setCategory(update.getCategory());
        skill.setDescription(update.getDescription());
        skill.setMinCompetencyScore(update.getMinCompetencyScore());

        return repo.save(skill);
    }

    @Override
    public Skill getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
    }

    @Override
    public List<Skill> getAllSkills() {
        return repo.findAll();
    }

    @Override
    public List<Skill> getActiveSkills() {
        return repo.findByActiveTrue();
    }

    @Override
    public void deactivateSkill(Long id) {
        Skill skill = getById(id);
        skill.setActive(false);
        repo.save(skill);
    }
}
