package com.example.demo.controller;

import com.example.demo.dto.SkillDTO;
import com.example.demo.service.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
@Tag(name = "Skill Management", description = "APIs for managing skills")
public class SkillController {
    
    private final SkillService skillService;
    
    @PostMapping
    @Operation(summary = "Create a new skill")
    public ResponseEntity<SkillDTO> createSkill(@Valid @RequestBody SkillDTO skillDTO) {
        return new ResponseEntity<>(skillService.createSkill(skillDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get skill by ID")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.getSkillById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all skills")
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update skill")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable Long id, @Valid @RequestBody SkillDTO skillDTO) {
        return ResponseEntity.ok(skillService.updateSkill(id, skillDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete skill")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/category/{category}")
    @Operation(summary = "Get skills by category")
    public ResponseEntity<List<SkillDTO>> getSkillsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(skillService.getSkillsByCategory(category));
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search skills by name")
    public ResponseEntity<List<SkillDTO>> searchSkills(@RequestParam String name) {
        return ResponseEntity.ok(skillService.searchSkillsByName(name));
    }
}