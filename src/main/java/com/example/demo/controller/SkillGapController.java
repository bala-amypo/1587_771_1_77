// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/gaps")
// public class SkillGapController {
// }



package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gaps")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @PostMapping("/compute/{studentId}")
    public ResponseEntity<List<SkillGapRecord>> compute(@PathVariable Long studentId) {
        return ResponseEntity.ok(skillGapService.computeGaps(studentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SkillGapRecord>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(skillGapService.getGapsByStudent(studentId));
    }
}