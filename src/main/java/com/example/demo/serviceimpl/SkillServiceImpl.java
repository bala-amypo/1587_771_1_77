
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
import com.example.demo.exception.ResourceNotFoundException;
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
        // Section 2.3 Rule: Uniqueness check
        if (repo.findByCode(skill.getCode()).isPresent()) {
            throw new IllegalArgumentException("unique code already exists");
        }

        // Section 2.3 Rule: minCompetencyScore must be between 0 and 100
        if (skill.getMinCompetencyScore() != null && 
           (skill.getMinCompetencyScore() < 0 || skill.getMinCompetencyScore() > 100)) {
            throw new IllegalArgumentException("Competency score must be between 0 and 100");
        }

        return repo.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        // Section 6.2 Rule: Throws ResourceNotFoundException if id missing
        Skill existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));

        existing.setName(skill.getName());
        existing.setCategory(skill.getCategory());
        existing.setMinCompetencyScore(skill.getMinCompetencyScore());
        
        return repo.save(existing);
    }

    @Override
    public Skill getById(Long id) {
        // Matches t048: message must contain "not found"
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
        Skill existing = getById(id);
        existing.setActive(false);
        repo.save(existing);
    }
}