package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gaps")
public class SkillGapController {

    private final SkillGapService service;

    public SkillGapController(SkillGapService service) {
        this.service = service;
    }

    @PostMapping("/compute/{studentId}")
    public ResponseEntity<List<SkillGapRecord>> compute(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.computeGaps(studentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SkillGapRecord>> getGaps(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getGapsByStudent(studentId));
    }
}
