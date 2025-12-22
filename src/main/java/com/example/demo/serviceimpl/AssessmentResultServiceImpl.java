package com.example.demo.service.impl;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.entity.Skill;
import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.AssessmentResultService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AssessmentResultServiceImpl implements AssessmentResultService {

    private final AssessmentResultRepository assessmentRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    public AssessmentResultServiceImpl(AssessmentResultRepository assessmentRepository,
                                       StudentProfileRepository studentProfileRepository,
                                       SkillRepository skillRepository) {
        this.assessmentRepository = assessmentRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public AssessmentResult recordResult(AssessmentResult result) {

        Long studentId = result.getStudentProfile().getId();
        StudentProfile studentProfile = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "StudentProfile not found with id " + studentId
                ));

        Long skillId = result.getSkill().getId();
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Skill not found with id " + skillId
                ));

        result.setStudentProfile(studentProfile);
        result.setSkill(skill);

        return assessmentRepository.save(result);
    }

    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentProfileId) {
        return assessmentRepository.findByStudentProfileId(studentProfileId);
    }

    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentProfileId, Long skillId) {
        return assessmentRepository
                .findByStudentProfileIdAndSkillId(studentProfileId, skillId);
    }
}
