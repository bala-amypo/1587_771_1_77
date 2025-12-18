@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository repository;

    public AssessmentResultServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssessmentResult> getResultsByStudentProfile(Long studentProfileId) {
        return repository.findByStudentProfile_Id(studentProfileId);
    }
}
