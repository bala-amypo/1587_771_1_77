@Service
public class RecommendationServiceImpl implements RecommendationService {
    
    @Autowired
    private SkillGapRecommendationRepository repository;

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return repository.findByStudentOrdered(studentId);
    }
}