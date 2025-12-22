@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final SkillGapRecommendationRepository recommendationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    public RecommendationServiceImpl(
            SkillGapRecommendationRepository recommendationRepository,
            StudentProfileRepository studentProfileRepository,
            SkillRepository skillRepository) {

        this.recommendationRepository = recommendationRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public List<RecommendationDTO> generateRecommendations(Long studentId) {

        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Skill> skills = skillRepository.findAll();

        List<RecommendationDTO> response = new ArrayList<>();

        for (Skill skill : skills) {

            // Example gap score logic (replace with real assessment logic)
            double gapScore = Math.random() * 100;

            SkillGapRecommendation rec = new SkillGapRecommendation();
            rec.setStudentProfile(student);
            rec.setSkill(skill);
            rec.setGapScore(gapScore);
            rec.setPriority(gapScore > 70 ? "HIGH" : "MEDIUM");
            rec.setRecommendedAction("Improve skill: " + skill.getName());
            rec.setGeneratedBy("SYSTEM");

            // 🔥 THIS WAS MISSING
            SkillGapRecommendation saved = recommendationRepository.save(rec);

            response.add(mapToDTO(saved));
        }

        return response;
    }

    @Override
    public List<RecommendationDTO> getRecommendationsByStudent(Long studentId) {

        return recommendationRepository.findByStudentProfile_Id(studentId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private RecommendationDTO mapToDTO(SkillGapRecommendation rec) {

        RecommendationDTO dto = new RecommendationDTO();
        dto.setSkillName(rec.getSkill().getName());
        dto.setGapScore(rec.getGapScore());
        dto.setPriority(rec.getPriority());
        dto.setRecommendedAction(rec.getRecommendedAction());
        dto.setGeneratedAt(rec.getGeneratedAt());

        return dto;
    }
}
