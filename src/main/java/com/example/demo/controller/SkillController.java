package com.example.demo.controller;

import com.example.demo.dto.SkillDTO;
import com.example.demo.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public ResponseEntity<SkillDTO> createSkill(@Valid @RequestBody SkillDTO dto) {
        SkillDTO created = skillService.createSkill(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable Long id) {
        SkillDTO skill = skillService.getSkillById(id);
        return ResponseEntity.ok(skill);
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        List<SkillDTO> skills = skillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<SkillDTO>> getSkillsByCategory(@PathVariable String category) {
        List<SkillDTO> skills = skillService.getSkillsByCategory(category);
        return ResponseEntity.ok(skills);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable Long id, @Valid @RequestBody SkillDTO dto) {
        SkillDTO updated = skillService.updateSkill(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = skillService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}
