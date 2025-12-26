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

    // ✅ REQUIRED by SkillService
    @Override
    public Skill createSkill(Skill skill) {

        // ✅ Repository supports findByCode
        repo.findByCode(skill.getCode()).ifPresent(s -> {
            throw new IllegalArgumentException("Skill code must be unique");
        });

        return repo.save(skill);
    }

    // ✅ REQUIRED by SkillService
    @Override
    public Skill updateSkill(Long id, Skill updatedSkill) {

        Skill existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        existing.setCode(updatedSkill.getCode());
        existing.setName(updatedSkill.getName());
        existing.setActive(updatedSkill.isActive());

        return repo.save(existing);
    }

    // ✅ REQUIRED by SkillService
    @Override
    public List<Skill> getActiveSkills() {
        return repo.findByActiveTrue();
    }

    // ✅ REQUIRED by SkillService
    @Override
    public List<Skill> getAllSkills() {
        return repo.findAll();
    }

    // ✅ REQUIRED by SkillService
    @Override
    public Skill getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    // ✅ REQUIRED by SkillService
    @Override
    public void deactivateSkill(Long id) {
        Skill skill = getById(id);
        skill.setActive(false);
        repo.save(skill);
    }
}
