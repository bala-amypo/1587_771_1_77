package com.example.demo.service;

import com.example.demo.dto.StudentProfileDTO;
import java.util.List;

public interface StudentProfileService {
    StudentProfileDTO createProfile(StudentProfileDTO profileDTO);
    StudentProfileDTO getProfileById(Long id);
    StudentProfileDTO getProfileByUserId(Long userId);
    List<StudentProfileDTO> getAllProfiles();
    StudentProfileDTO updateProfile(Long id, StudentProfileDTO profileDTO);
    void deleteProfile(Long id);
    List<StudentProfileDTO> getProfilesByDepartment(String department);
}
