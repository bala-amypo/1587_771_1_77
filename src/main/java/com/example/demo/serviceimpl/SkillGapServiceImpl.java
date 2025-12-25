package com.example.demo.serviceImpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;

import java.util.ArrayList;
import java.util.List;

public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository gapRepo;
    private final AssessmentResultRepository assessmentRepo;
    private final StudentProfileRepository profileRepo;
    private final SkillRepository skillRepo;

    public SkillGapServiceImpl(
            SkillGapRecordRepository gapRepo,
            AssessmentResultRepository assessmentRepo,
            StudentProfileRepository profileRepo,
            SkillRepository skillRepo
    ) {
        this.gapRepo = gapRepo;
        this.assessmentRepo = assessmentRepo;
        this.profileRepo = profileRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public SkillGapRecord computeGap(Long studentId, Long skillId) {

        StudentProfile student = profileRepo.findById(studentId).orElseThrow();
        Skill skill = skillRepo.findById(skillId).orElseThrow();

        var results =
                assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skillId);

        double score = results.isEmpty() ? 0 : results.get(0).getScore();

        SkillGapRecord rec = SkillGapRecord.builder()
                .studentProfile(student)
                .skill(skill)
                .currentScore(score)
                .targetScore(100.0)
                .build();

        return gapRepo.save(rec);
    }

    @Override
    public List<SkillGapRecord> computeAllGaps(Long studentId) {

        List<Skill> skills = skillRepo.findByActiveTrue();

        List<SkillGapRecord> list = new ArrayList<>();

        for (Skill s : skills) {
            list.add(computeGap(studentId, s.getId()));
        }

        return list;
    }

    @Override
    public List<SkillGapRecord> getGapHistory(Long studentId) {
        return gapRepo.findByStudentProfileId(studentId);
    }
}
