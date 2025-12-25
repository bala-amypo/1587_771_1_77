package com.example.demo.controller;

import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService service;

    public SkillController(SkillService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Skill create(@RequestBody Skill s) {
        return service.createSkill(s);
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody Skill s) {
        return service.updateSkill(id, s);
    }

    @GetMapping("/{id}")
    public Skill get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/")
    public List<Skill> listActive() {
        return service.getActiveSkills();
    }
}
