
package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill createSkill(Skill skill) {

        skillRepository.findByCode(skill.getCode())
                .ifPresent(s -> {
                    throw new RuntimeException("Skill already exists: " + s.getCode());
                });

        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill updated) {

        Skill existing = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        existing.setCode(updated.getCode());
        existing.setCategory(updated.getCategory());
        existing.setDescription(updated.getDescription());
        existing.setMinCompetencyScore(updated.getMinCompetencyScore());
        existing.setActive(updated.getActive());

        return skillRepository.save(existing);
    }

    @Override
    public Skill getById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public List<Skill> getActiveSkills() {
        return skillRepository.findByActiveTrue();
    }

    @Override
    public void deactivateSkill(Long id) {
        Skill skill = getById(id);
        skill.setActive(false);
        skillRepository.save(skill);
    }
}

