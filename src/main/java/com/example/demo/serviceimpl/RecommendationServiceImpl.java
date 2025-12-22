package com.example.demo.serviceimpl;

import com.example.demo.dto.RecommendationDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final StudentProfileRepository studentRepo;
    private final SkillRepository skillRepo;
    private final SkillGapRecommendationRepository recommendationRepo;

    public RecommendationServiceImpl(StudentProfileRepository studentRepo,
                                      SkillRepository skillRepo,
                                      SkillGapRecommendationRepository recommendationRepo) {
        this.studentRepo = studentRepo;
        this.skillRepo = skillRepo;
        this.recommendationRepo = recommendationRepo;
    }

    @Override
    public List<RecommendationDTO> generateRecommendations(Long studentId) {

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Skill> skills = skillRepo.findAll();
        List<RecommendationDTO> response = new ArrayList<>();

        for (Skill skill : skills) {

            if (skill.getMinCompetencyScore() != null &&
                student.getCompetencyScore() < skill.getMinCompetencyScore()) {

                SkillGapRecommendation rec = new SkillGapRecommendation();
                rec.setStudent(student);
                rec.setSkill(skill);
                rec.setPriority(Priority.HIGH);
                rec.setRecommendedAction(
                        "Improve skill: " + skill.getSkillName()
                );

                recommendationRepo.save(rec);

                RecommendationDTO dto = new RecommendationDTO();
                dto.setSkillName(skill.getSkillName());
                dto.setPriority(rec.getPriority().name());
                dto.setRecommendation(rec.getRecommendedAction());
                dto.setGeneratedAt(LocalDateTime.now());

                response.add(dto);
            }
        }
        return response;
    }
}
