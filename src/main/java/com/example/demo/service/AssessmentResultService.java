public interface AssessmentResultService {

    AssessmentResult saveAssessmentResult(
            String subject,
            double scoreObtained,
            Long studentId,
            Long skillId
    );

    List<AssessmentResult> getResultsByStudentId(Long studentId);

    List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId);
}
