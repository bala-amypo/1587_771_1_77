package com.example.demo.controller;

import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@Tag(name = "Skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // POST /api/skills
    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }

    // PUT /api/skills/{id}
    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill);
    }

    // GET /api/skills/{id}
    @GetMapping("/{id}")
    public Skill getSkill(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    // GET /api/skills
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    // PUT /api/skills/{id}/deactivate
    @PutMapping("/{id}/deactivate")
    public void deactivateSkill(@PathVariable Long id) {
        skillService.deactivateSkill(id);
    }
}
