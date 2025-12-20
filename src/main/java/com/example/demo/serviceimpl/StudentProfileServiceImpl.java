package com.example.demo.serviceimpl;

import com.example.demo.dto.StudentProfileDTO;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentProfileServiceImpl implements StudentProfileService {
    
    private final StudentProfileRepository studentProfileRepository;
    
    @Override
    @Transactional
    public StudentProfileDTO createProfile(StudentProfileDTO profileDTO) {
        if (studentProfileRepository.existsByUserId(profileDTO.getUserId())) {
            throw new DuplicateResourceException("Profile already exists for user id: " + profileDTO.getUserId());
        }
        
        StudentProfile profile = mapToEntity(profileDTO);
        StudentProfile savedProfile = studentProfileRepository.save(profile);
        return mapToDTO(savedProfile);
    }
    
    @Override
    public StudentProfileDTO getProfileById(Long id) {
        StudentProfile profile = studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found with id: " + id));
        return mapToDTO(profile);
    }
    
    @Override
    public StudentProfileDTO getProfileByUserId(Long userId) {
        StudentProfile profile = studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found for user id: " + userId));
        return mapToDTO(profile);
    }
    
    @Override
    public List<StudentProfileDTO> getAllProfiles() {
        return studentProfileRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public StudentProfileDTO updateProfile(Long id, StudentProfileDTO profileDTO) {
        StudentProfile profile = studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found with id: " + id));
        
        profile.setUserId(profileDTO.getUserId());
        profile.setDepartment(profileDTO.getDepartment());
        profile.setCurrentSemester(profileDTO.getCurrentSemester());
        profile.setCareerGoal(profileDTO.getCareerGoal());
        profile.setInterests(profileDTO.getInterests());
        
        StudentProfile updatedProfile = studentProfileRepository.save(profile);
        return mapToDTO(updatedProfile);
    }
    
    @Override
    @Transactional
    public void deleteProfile(Long id) {
        if (!studentProfileRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student profile not found with id: " + id);
        }
        studentProfileRepository.deleteById(id);
    }
    
    @Override
    public List<StudentProfileDTO> getProfilesByDepartment(String department) {
        return studentProfileRepository.findByDepartment(department).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    private StudentProfileDTO mapToDTO(StudentProfile profile) {
        return new StudentProfileDTO(
                profile.getId(),
                profile.getUserId(),
                profile.getDepartment(),
                profile.getCurrentSemester(),
                profile.getCareerGoal(),
                profile.getInterests()
        );
    }
    
    private StudentProfile mapToEntity(StudentProfileDTO dto) {
        return new StudentProfile(
                dto.getId(),
                dto.getUserId(),
                dto.getDepartment(),
                dto.getCurrentSemester(),
                dto.getCareerGoal(),
                dto.getInterests()
        );
    }
}
