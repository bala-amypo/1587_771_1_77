package com.example.demo.controller;
import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;
import com.example.demo.repository.SkillGapRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/gaps")
public class SkillGapController {
    @Autowired
    private SkillGapService service;
    @Autowired
    private SkillGapRecordRepository repository;
    @PostMapping("/compute/{studentId}")
    public String compute(@PathVariable Long studentId) {
        service.computeSkillGaps(studentId);
        return "Skill gaps computed successfully";
    }
    @GetMapping("/student/{studentId}")
    public List<SkillGapRecord> getGaps(@PathVariable Long studentId) {
        return repository.findByStudentProfileId(studentId);
    }
}
