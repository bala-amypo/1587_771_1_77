
// package com.example.demo.serviceimpl;

// import com.example.demo.entity.Skill;
// import com.example.demo.repository.SkillRepository;

// import java.util.List;

// public class SkillServiceImpl {

//     private final SkillRepository repo;

//     public SkillServiceImpl(SkillRepository repo) {
//         this.repo = repo;
//     }

//     public Skill createSkill(Skill skill) {
//         if (repo.findByCode(skill.getCode()).isPresent()) {
//             throw new IllegalArgumentException("unique");
//         }
//         return repo.save(skill);
//     }

//     public Skill updateSkill(Long id, Skill skill) {
//         Skill existing = repo.findById(id)
//                 .orElseThrow(() -> new RuntimeException("not found"));

//         existing.setName(skill.getName());
//         return repo.save(existing);
//     }

//     public Skill getById(Long id) {
//         return repo.findById(id)
//                 .orElseThrow(() -> new RuntimeException("not found"));
//     }

//     public List<Skill> getActiveSkills() {
//         return repo.findByActiveTrue();
//     }
// }





package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.repository.SkillRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillServiceImpl {

    private final SkillRepository repo;

    public SkillServiceImpl(SkillRepository repo) {
        this.repo = repo;
    }

    public Skill createSkill(Skill skill) {
        // Requirement: check uniqueness and throw message with "unique" keyword
        if (repo.findByCode(skill.getCode()).isPresent()) {
            throw new IllegalArgumentException("skill code must be unique");
        }
        
        // Ensure score validation is triggered if not using @PrePersist
        if (skill.getMinCompetencyScore() != null && (skill.getMinCompetencyScore() < 0 || skill.getMinCompetencyScore() > 100)) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        return repo.save(skill);
    }

    public Skill updateSkill(Long id, Skill skill) {
        Skill existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("skill not found"));

        existing.setName(skill.getName());
        existing.setCategory(skill.getCategory());
        existing.setMinCompetencyScore(skill.getMinCompetencyScore());
        
        return repo.save(existing);
    }

    public Skill getById(Long id) {
        // Requirement: message must contain "not found"
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("skill not found"));
    }

    public List<Skill> getAllSkills() {
        return repo.findAll();
    }

    public List<Skill> getActiveSkills() {
        return repo.findByActiveTrue();
    }

    public void deactivateSkill(Long id) {
        Skill skill = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("skill not found"));
        skill.setActive(false);
        repo.save(skill);
    }
}