public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

    List<AssessmentResult> findByStudentProfile_Id(Long studentProfileId);

    List<AssessmentResult> findByStudentProfile_IdAndSkill_Id(Long studentProfileId, Long skillId);
}
