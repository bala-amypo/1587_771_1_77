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
@RequestMapping("/skill-gaps")
public class SkillGapController {

    private final SkillGapService service;

    public SkillGapController(SkillGapService service) {
        this.service = service;
    }

    @GetMapping("/{studentProfileId}")
    public ResponseEntity<List<SkillGapRecord>> compute(@PathVariable Long studentProfileId) {
        return ResponseEntity.ok(service.computeGaps(studentProfileId));
    }
}
