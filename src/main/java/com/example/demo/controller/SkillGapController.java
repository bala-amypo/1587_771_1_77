package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gaps")
@Tag(name = "Skill Gaps", description = "Compute and retrieve skill gap records")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @PostMapping("/compute/{studentId}")
    public void computeGaps(@PathVariable Long studentId) {
        skillGapService.computeGaps(studentId);
    }

    @GetMapping("/student/{studentId}")
    public List<SkillGapRecord> getGapsByStudent(@PathVariable Long studentId) {
        return skillGapService.getGapsByStudent(studentId);
    }
}
