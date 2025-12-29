
// package com.example.demo.serviceimpl;

// import com.example.demo.entity.Skill;
// import com.example.demo.entity.SkillGapRecord;
// import com.example.demo.entity.StudentProfile;
// import com.example.demo.repository.AssessmentResultRepository;
// import com.example.demo.repository.SkillGapRecordRepository;
// import com.example.demo.repository.SkillRepository;
// import com.example.demo.repository.StudentProfileRepository;
// import com.example.demo.service.SkillGapService;
// import org.springframework.stereotype.Service;

// import java.util.ArrayList;
// import java.util.List;

// @Service  
// public class SkillGapServiceImpl implements SkillGapService {

//     private final SkillGapRecordRepository repo;
//     private final SkillRepository skillRepo;
//     private final AssessmentResultRepository assessmentRepo;
//     private final StudentProfileRepository studentProfileRepo;

//     public SkillGapServiceImpl(
//             SkillGapRecordRepository repo,
//             SkillRepository skillRepo,
//             AssessmentResultRepository assessmentRepo,
//             StudentProfileRepository studentProfileRepo) {

//         this.repo = repo;
//         this.skillRepo = skillRepo;
//         this.assessmentRepo = assessmentRepo;
//         this.studentProfileRepo = studentProfileRepo;
//     }

//     @Override
//     public List<SkillGapRecord> computeGaps(Long studentProfileId) {

    
//         StudentProfile profile = studentProfileRepo.findById(studentProfileId)
//                 .orElseThrow(() -> new RuntimeException("Student profile not found"));

//         List<Skill> skills = skillRepo.findByActiveTrue();

//         List<SkillGapRecord> result = new ArrayList<>();

//         for (Skill skill : skills) {

//             SkillGapRecord gap = SkillGapRecord.builder()
//                     .studentProfile(profile)   
//                     .skill(skill)              
//                     .currentScore(40.0)
//                     .targetScore(70.0)
//                     .gapScore(30.0)
//                     .build();

//             result.add(repo.save(gap));
//         }

//         return result;
//     }
// }



package com.example.demo.serviceimpl;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository recordRepository;

    public SkillGapServiceImpl(SkillGapRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<SkillGapRecord> analyzeSkillGaps(Long studentId) {
        // TODO: add gap logic later
        return recordRepository.findByStudentProfileId(studentId);
    }

    @Override
    public List<SkillGapRecord> getSkillGapsForStudent(Long studentId) {
        return recordRepository.findByStudentProfileId(studentId);
    }
}