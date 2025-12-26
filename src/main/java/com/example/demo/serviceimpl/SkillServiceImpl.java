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

        // ✅ FIX: Skill has NO getName(), use getCode()
        repo.findByCode(skill.getCode()).ifPresent(s -> {
            throw new IllegalArgumentException("Skill code must be unique");
        });

        return repo.save(skill);
    }

    @Override
    public List<Skill> getActiveSkills() {
        return repo.findByActiveTrue();
    }

    @Override
    public List<Skill> getAllSkills() {
        return repo.findAll();   // ✅ FIX: implement missing method
    }

    @Override
    public Skill getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    @Override
    public void deactivateSkill(Long id) {
        Skill skill = getById(id);
        skill.setActive(false);
        repo.save(skill);
    }
}
