@Repository
public interface AssessmentResultRepository
        extends JpaRepository<AssessmentResult, Long> {

    Optional<AssessmentResult> findByStudentIdAndSkillId(
            Long studentId,
            Long skillId
    );
}
