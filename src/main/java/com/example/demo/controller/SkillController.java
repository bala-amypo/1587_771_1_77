
// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/skills")
// public class SkillController {
// }
package com.example.demo.controller;

import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService service;

    public SkillController(SkillService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Skill> create(@RequestBody Skill skill) {
        return ResponseEntity.ok(service.createSkill(skill));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> update(@PathVariable Long id, @RequestBody Skill skill) {
        return ResponseEntity.ok(service.updateSkill(id, skill));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Skill>> active() {
        return ResponseEntity.ok(service.getActiveSkills());
    }
}
