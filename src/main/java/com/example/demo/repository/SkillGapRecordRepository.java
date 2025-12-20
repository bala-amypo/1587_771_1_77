@Repository
public interface SkillGapRecordRepository 
        extends JpaRepository<SkillGapRecord, Long> {

    List<SkillGapRecord> findByStudentProfileId(Long studentProfileId);
}
