package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository repository;

    @Override
    public Skill createSkill(Skill skill) {

        repository.findByCode(skill.getCode())
                .ifPresent(s -> { throw new IllegalArgumentException("Code must be unique"); });

        return repository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill updated) {

        Skill existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        if (updated.getName() != null)
            existing.setName(updated.getName());

        if (updated.getCode() != null)
            existing.setCode(updated.getCode());

        return repository.save(existing);
    }

    @Override
    public Skill getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    @Override
    public List<Skill> getActiveSkills() {
        return repository.findByActiveTrue();
    }
}
