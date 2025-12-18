import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    @ManyToOne
    private Skill skill;

    private Double scoreObtained;
    private Double maxScore;
    private Timestamp assessedAt;

    // Getters & Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }
    
    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public Skill getSkill() {
        return skill;
    }
    
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Double getScoreObtained() {
        return scoreObtained;
    }
    
    public void setScoreObtained(Double scoreObtained) {
        this.scoreObtained = scoreObtained;
    }

    public Double getMaxScore() {
        return maxScore;
    }
    
    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Timestamp getAssessedAt() {
        return assessedAt;
    }
    
    public void setAssessedAt(Timestamp assessedAt) {
        this.assessedAt = assessedAt;
    }
}
