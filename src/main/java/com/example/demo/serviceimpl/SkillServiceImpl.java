
package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;

import java.util.List;

public class SkillServiceImpl {

    private final SkillRepository repo;

    public SkillServiceImpl(SkillRepository repo) {
        this.repo = repo;
    }

    public Skill createSkill(Skill skill) {
        if (repo.findByCode(skill.getCode()).isPresent()) {
            throw new IllegalArgumentException("unique");
        }
        return repo.save(skill);
    }

    public Skill updateSkill(Long id, Skill skill) {
        Skill existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        existing.setName(skill.getName());
        return repo.save(existing);
    }

    public Skill getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<Skill> getActiveSkills() {
        return repo.findByActiveTrue();
    }
}
