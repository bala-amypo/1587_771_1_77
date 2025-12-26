package com.example.demo.controller;

import com.example.demo.service.SkillGapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gaps")
public class SkillGapController {
    private final SkillGapService gapService;

    public SkillGapController(SkillGapService gapService) {
        this.gapService = gapService;
    }

    @PostMapping("/compute/{studentId}")
    public ResponseEntity<?> compute(@PathVariable Long studentId) {
        return ResponseEntity.ok(gapService.computeGaps(studentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(gapService.getGapsByStudent(studentId));
    }
}