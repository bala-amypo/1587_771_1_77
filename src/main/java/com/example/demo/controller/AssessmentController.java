@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentResultService service;

    public AssessmentController(AssessmentResultService service) {
        this.service = service;
    }

    // ✅ POST – Save Assessment (DTO based)
    @PostMapping
    public AssessmentResult saveAssessment(
            @RequestBody AssessmentResultRequest request) {

        return service.saveAssessmentResult(
                request.getSubject(),
                request.getScoreObtained(),
                request.getStudentId(),
                request.getSkillId()
        );
    }

    // ✅ GET – Student results
    @GetMapping("/student/{studentId}")
    public List<AssessmentResult> getByStudent(@PathVariable Long studentId) {
        return service.getResultsByStudentId(studentId);
    }

    // ✅ GET – Student + Skill
    @GetMapping("/student/{studentId}/skill/{skillId}")
    public List<AssessmentResult> getByStudentAndSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId) {

        return service.getResultsByStudentAndSkill(studentId, skillId);
    }
}
