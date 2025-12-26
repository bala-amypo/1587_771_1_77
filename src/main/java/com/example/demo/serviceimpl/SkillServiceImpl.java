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

        repository.findByCode(skill.getCode()).ifPresent(s -> {
            throw new IllegalArgumentException("Skill code must be unique");
        });

        if (skill.getMinCompetencyScore() != null &&
            (skill.getMinCompetencyScore() < 0 ||
             skill.getMinCompetencyScore() > 100)) {
            throw new IllegalArgumentException("minCompetencyScore must be between 0 and 100");
        }

        return repository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill update) {
        Skill existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill not found"));

        existing.setName(update.getName());
        existing.setCode(update.getCode());
        existing.setCategory(update.getCategory());
        existing.setDescription(update.getDescription());
        existing.setMinCompetencyScore(update.getMinCompetencyScore());

        return repository.save(existing);
    }

    @Override
    public Skill getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill not found"));
    }

    @Override
    public List<Skill> getAllSkills() {
        return repository.findAll();
    }

    @Override
    public List<Skill> getActiveSkills() {
        return repository.findByActiveTrue();
    }

    @Override
    public void deactivateSkill(Long id) {
        Skill skill = getById(id);
        skill.setActive(false);
        repository.save(skill);
    }
}
