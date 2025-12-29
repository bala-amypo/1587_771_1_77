
// package com.example.demo.controller;

// import com.example.demo.entity.SkillGapRecord;
// import com.example.demo.service.SkillGapService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/skill-gaps")
// public class SkillGapController {

//     private final SkillGapService service;

//     public SkillGapController(SkillGapService service) {
//         this.service = service;
//     }

//     @GetMapping("/{studentProfileId}")
//     public ResponseEntity<List<SkillGapRecord>> compute(@PathVariable Long studentProfileId) {
//         return ResponseEntity.ok(service.computeGaps(studentProfileId));
//     }
// }



package com.example.demo.controller;

import com.example.demo.service.SkillGapService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skill-gaps")
@Tag(name = "Skill Gaps")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @PostMapping("/analyze/{studentId}")
    public ResponseEntity<?> analyze(@PathVariable Long studentId) {
        return ResponseEntity.ok(
                skillGapService.analyzeSkillGaps(studentId)
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getSkillGaps(@PathVariable Long studentId) {
        return ResponseEntity.ok(
                skillGapService.getSkillGapsForStudent(studentId)
        );
    }
}