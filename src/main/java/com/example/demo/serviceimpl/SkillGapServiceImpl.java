// package com.example.demo.serviceimpl;

// import com.example.demo.entity.SkillGapRecord;
// import com.example.demo.repository.SkillGapRecordRepository;
// import com.example.demo.repository.SkillRepository;
// import com.example.demo.repository.AssessmentResultRepository;
// import java.util.Collections;
// import java.util.List;

// public class SkillGapServiceImpl {

//     public SkillGapServiceImpl(
//             SkillGapRecordRepository repo,
//             SkillRepository skillRepo,
//             AssessmentResultRepository assessmentRepo) {
//     }

//     public List<SkillGapRecord> computeGaps(Long studentProfileId) {
//         return Collections.emptyList();
//     }
// }









package com.example.demo.serviceimpl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.entity.Skill;
import com.example.demo.entity.SkillGapRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.SkillGapService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository gapRepo;
    private final SkillRepository skillRepo;
    private final AssessmentResultRepository assessmentRepo;
    private final StudentProfileRepository profileRepo;

    // Constructor injection as per Section 6.4
    public SkillGapServiceImpl(
            SkillGapRecordRepository gapRepo,
            SkillRepository skillRepo,
            AssessmentResultRepository assessmentRepo,
            StudentProfileRepository profileRepo) {
        this.gapRepo = gapRepo;
        this.skillRepo = skillRepo;
        this.assessmentRepo = assessmentRepo;
        this.profileRepo = profileRepo;
    }

    @Override
    @Transactional
    public List<SkillGapRecord> computeGaps(Long studentProfileId) {
        // Fetch the student profile
        StudentProfile profile = profileRepo.findById(studentProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));

        // Fetch all active skills
        List<Skill> activeSkills = skillRepo.findByActiveTrue();
        List<SkillGapRecord> calculatedGaps = new ArrayList<>();

        for (Skill skill : activeSkills) {
            // Fetch assessments for this specific student and skill
            List<AssessmentResult> assessments = assessmentRepo
                    .findByStudentProfileIdAndSkillId(studentProfileId, skill.getId());

            // Use the latest score if available, otherwise 0.0
            double currentScore = assessments.isEmpty() ? 0.0 : assessments.get(0).getScore();
            double targetScore = skill.getMinCompetencyScore();
            double gapValue = targetScore - currentScore;

            // Section 2.5 Rule: positive gaps indicate deficiency
            SkillGapRecord record = SkillGapRecord.builder()
                    .studentProfile(profile)
                    .skill(skill)
                    .currentScore(currentScore)
                    .targetScore(targetScore)
                    .gapScore(gapValue)
                    .build();

            calculatedGaps.add(gapRepo.save(record));
        }

        return calculatedGaps;
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentId) {
        return gapRepo.findByStudentProfileId(studentId);
    }
}