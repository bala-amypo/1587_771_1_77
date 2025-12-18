package com.example.demo.controller;
import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/skills")
public class SkillController {
    @Autowired
    private SkillService service;
    @PostMapping
    public Skill add(@RequestBody Skill skill) {
        return service.addSkill(skill);
    }
    @GetMapping
    public List<Skill> getAll() {
        return service.getAllSkills();
    }
    @PutMapping("/{id}/deactivate")
    public Skill deactivate(@PathVariable Long id) {
        return service.deactivateSkill(id);
    }
}
