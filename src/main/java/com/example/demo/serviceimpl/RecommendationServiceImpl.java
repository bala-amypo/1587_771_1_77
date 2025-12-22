package com.example.demo.service.impl;

import com.example.demo.dto.RecommendationDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RecommendationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final SkillGapRecommendationRepository recommendationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;

    public RecommendationServiceImpl(
            SkillGapRecommendationRepository recommendationRepository,
            StudentProfileRepository studentProfileRepository,
            SkillRepository skillRepository) {

        this.recommendationRepository = recommendationRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.skillRepository = skillRepository;
    }

    @Transactional
    @Override
    public List<RecommendationDTO> generateRecommendations(Long studentId) {

        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Skill> skills = skillRepository.findAll();
        List<RecommendationDTO> response = new ArrayList<>();

        for (Skill skill : skills) {

            SkillGapRecommendation rec = new SkillGapRecommendation();
            rec.setStudentProfile(student);
            rec.setSkill(skill);
            rec.setGapScore(Math.random() * 100);
            rec.setPriority(Priority.MEDIUM);
            rec.setRecommendedAction("Improve skill: " + skill.getName());
            rec.setGeneratedBy("SYSTEM");

            SkillGapRecommendation saved = recommendationRepository.save(rec);
            response.add(mapToDTO(saved));
        }

        return response;
    }

    @Override
    public List<RecommendationDTO> getRecommendationsByStudent(Long studentId) {

        return recommendationRepository.findByStudentProfile_Id(studentId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private RecommendationDTO mapToDTO(SkillGapRecommendation rec) {

        RecommendationDTO dto = new RecommendationDTO();
        dto.setSkillName(rec.getSkill().getName());
        dto.setGapScore(rec.getGapScore());
        dto.setPriority(rec.getPriority().name());
        dto.setRecommendedAction(rec.getRecommendedAction());
        dto.setGeneratedAt(rec.getGeneratedAt());

        return dto;
    }
}
