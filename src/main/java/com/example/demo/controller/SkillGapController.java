package com.example.demo.controller;

import com.example.demo.dto.SkillGapRecordDTO;
import com.example.demo.service.SkillGapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/skillgaps")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SkillGapController {

    private final SkillGapService skillGapService;

    @PostMapping
    public ResponseEntity<SkillGapRecordDTO> createSkillGap(@Valid @RequestBody SkillGapRecordDTO dto) {
        SkillGapRecordDTO created = skillGapService.createSkillGap(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillGapRecordDTO> getSkillGapById(@PathVariable Long id) {
        SkillGapRecordDTO skillGap = skillGapService.getSkillGapById(id);
        return ResponseEntity.ok(skillGap);
    }

    @GetMapping
    public ResponseEntity<List<SkillGapRecordDTO>> getAllSkillGaps() {
        List<SkillGapRecordDTO> skillGaps = skillGapService.getAllSkillGaps();
        return ResponseEntity.ok(skillGaps);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SkillGapRecordDTO>> getSkillGapsByStudent(@PathVariable Long studentId) {
        List<SkillGapRecordDTO> skillGaps = skillGapService.getSkillGapsByStudentId(studentId);
        return ResponseEntity.ok(skillGaps);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillGapRecordDTO> updateSkillGap(@PathVariable Long id, @Valid @RequestBody SkillGapRecordDTO dto) {
        SkillGapRecordDTO updated = skillGapService.updateSkillGap(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkillGap(@PathVariable Long id) {
        skillGapService.deleteSkillGap(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/analyze/{studentId}")
    public ResponseEntity<List<SkillGapRecordDTO>> analyzeSkillGaps(@PathVariable Long studentId) {
        List<SkillGapRecordDTO> skillGaps = skillGapService.analyzeSkillGapsForStudent(studentId);
        return ResponseEntity.ok(skillGaps);
    }
}