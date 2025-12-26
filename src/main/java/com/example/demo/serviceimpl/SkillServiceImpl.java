package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository repo;

    public SkillServiceImpl(SkillRepository repo) {
        this.repo = repo;
    }

    @Override
    public Skill createSkill(Skill skill) {

        // ✅ Skill has FIELD `code` (not getter)
        repo.findByCode(skill.code).ifPresent(s -> {
            throw new IllegalArgumentException("Skill code must be unique");
        });

        return repo.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill updatedSkill) {

        Skill existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        // ✅ FIELD access (NO getters/setters)
        existing.code = updatedSkill.code;
        existing.name = updatedSkill.name;
        existing.active = updatedSkill.active;

        return repo.save(existing);
    }

    @Override
    public List<Skill> getActiveSkills() {
        return repo.findByActiveTrue();
    }

    @Override
    public List<Skill> getAllSkills() {
        return repo.findAll();
    }

    @Override
    public Skill getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    @Override
    public void deactivateSkill(Long id) {
        Skill skill = getById(id);
        skill.active = false;
        repo.save(skill);
    }
}
