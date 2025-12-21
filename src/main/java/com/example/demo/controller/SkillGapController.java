package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gaps")
@Tag(name = "Skill Gaps")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    // POST /api/gaps/compute/{studentId}
    @PostMapping("/compute/{studentId}")
    public List<SkillGapRecord> computeGaps(@PathVariable Long studentId) {
        return skillGapService.computeGaps(studentId);
    }

    // GET /api/gaps/student/{studentId}
    @GetMapping("/student/{studentId}")
    public List<SkillGapRecord> getGaps(@PathVariable Long studentId) {
        return skillGapService.getGapsByStudent(studentId);
    }
}
