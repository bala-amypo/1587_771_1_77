@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository assessmentRepo;
    private final StudentProfileRepository studentRepo;
    private final SkillRepository skillRepo;

    public AssessmentResultServiceImpl(
            AssessmentResultRepository assessmentRepo,
            StudentProfileRepository studentRepo,
            SkillRepository skillRepo) {

        this.assessmentRepo = assessmentRepo;
        this.studentRepo = studentRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public AssessmentResult saveAssessmentResult(
            String subject,
            double scoreObtained,
            Long studentId,
            Long skillId) {

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Skill skill = skillRepo.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        AssessmentResult result = new AssessmentResult();
        result.setSubject(subject);
        result.setScoreObtained(scoreObtained);
        result.setStudentProfile(student);
        result.setSkill(skill);

        return assessmentRepo.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentId(Long studentId) {
        return assessmentRepo.findByStudentProfile_Id(studentId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        return assessmentRepo.findByStudentProfile_IdAndSkill_Id(studentId, skillId);
    }
}
