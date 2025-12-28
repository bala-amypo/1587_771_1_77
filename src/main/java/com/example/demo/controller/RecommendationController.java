
// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/recommendations")
// public class RecommendationController {
// }
package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping("/{studentId}/{skillId}")
    public ResponseEntity<SkillGapRecommendation> compute(
            @PathVariable Long studentId,
            @PathVariable Long skillId) {

        return ResponseEntity.ok(
                service.computeRecommendationForStudentSkill(studentId, skillId)
        );
    }

    @PostMapping("/student/{studentId}")
    public ResponseEntity<List<SkillGapRecommendation>> computeAll(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.computeRecommendationsForStudent(studentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SkillGapRecommendation>> get(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getRecommendationsForStudent(studentId));
    }
}
