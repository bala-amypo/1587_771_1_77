
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillServiceImpl {

    private final SkillRepository repo;

    public SkillServiceImpl(SkillRepository repo) {
        this.repo = repo;
    }

    public Skill createSkill(Skill skill) {
        // Validation for uniqueness (Existing)
        if (repo.findByCode(skill.getCode()).isPresent()) {
            throw new IllegalArgumentException("unique");
        }
        
        // ADDED: Validation for minCompetencyScore (Requirement)
        if (skill.getMinCompetencyScore() < 0) {
            throw new IllegalArgumentException("minCompetencyScore must be positive");
        }
        
        return repo.save(skill);
    }

    public Skill updateSkill(Long id, Skill skill) {
        Skill existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));

        existing.setName(skill.getName());
        // Added setting the code/score if necessary based on your skill entity fields
        return repo.save(existing);
    }

    public Skill getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    // ADDED: Requirement for List All
    public List<Skill> getAllSkills() {
        return repo.findAll();
    }

    public List<Skill> getActiveSkills() {
        return repo.findByActiveTrue();
    }

    // ADDED: Requirement for deactivateSkill
    @Transactional
    public void deactivateSkill(Long id) {
        Skill skill = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        skill.setActive(false);
        repo.save(skill);
    }
}
