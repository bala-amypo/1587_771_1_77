package com.example.demo.controller;

import com.example.demo.dto.SkillGapRecordDTO;
import com.example.demo.service.SkillGapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill-gaps")
@RequiredArgsConstructor
@Tag(name = "Skill Gap Analysis", description = "APIs for managing skill gap records")
public class SkillGapController {
    
    private final SkillGapService skillGapService;
    
    @PostMapping
    @Operation(summary = "Create skill gap record")
    public ResponseEntity<SkillGapRecordDTO> createSkillGapRecord(@Valid @RequestBody SkillGapRecordDTO recordDTO) {
        return new ResponseEntity<>(skillGapService.createSkillGapRecord(recordDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get skill gap record by ID")
    public ResponseEntity<SkillGapRecordDTO> getSkillGapRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(skillGapService.getSkillGapRecordById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all skill gap records")
    public ResponseEntity<List<SkillGapRecordDTO>> getAllSkillGapRecords() {
        return ResponseEntity.ok(skillGapService.getAllSkillGapRecords());
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get skill gap records by user ID")
    public ResponseEntity<List<SkillGapRecordDTO>> getSkillGapRecordsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(skillGapService.getSkillGapRecordsByUserId(userId));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update skill gap record")
    public ResponseEntity<SkillGapRecordDTO> updateSkillGapRecord(@PathVariable Long id, @Valid @RequestBody SkillGapRecordDTO recordDTO) {
        return ResponseEntity.ok(skillGapService.updateSkillGapRecord(id, recordDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete skill gap record")
    public ResponseEntity<Void> deleteSkillGapRecord(@PathVariable Long id) {
        skillGapService.deleteSkillGapRecord(id);
        return ResponseEntity.noContent().build();
    }
}
