package com.example.demo.serviceimpl;

import com.example.demo.dto.AssessmentRequest;
import com.example.demo.entity.AssessmentResult;
import com.example.demo.entity.Skill;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.AssessmentResultService;
import org.springframework.stereotype.Service;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository assessmentRepo;
    private final StudentProfileRepository studentRepo;
    private final SkillRepository skillRepo;

    public AssessmentResultServiceImpl(
            AssessmentResultRepository assessmentRepo,
            StudentProfileRepository studentRepo,
            SkillRepository skillRepo) {
        this.assessmentRepo = assessmentRepo;
        this.studentRepo = studentRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public AssessmentResult saveAssessment(AssessmentRequest request) {

        StudentProfile student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Skill skill = skillRepo.findById(request.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        AssessmentResult result = new AssessmentResult();
        result.setSubject(request.getSubject());
        result.setScoreObtained(request.getScoreObtained());
        result.setStudentProfile(student);
        result.setSkill(skill);

        return assessmentRepo.save(result);
    }
}
