
// --- StudentProfileService.java ---
package com.example.demo.service;

import com.example.demo.dto.StudentProfileDTO;
import java.util.List;
import java.util.Map;

public interface StudentProfileService {
    StudentProfileDTO createStudentProfile(StudentProfileDTO profileDTO);
    StudentProfileDTO getStudentProfileById(Long id);
    List<StudentProfileDTO> getAllStudentProfiles();
    StudentProfileDTO getStudentProfileByUserId(Long userId);
    StudentProfileDTO updateStudentProfile(Long id, StudentProfileDTO profileDTO);
    void deleteStudentProfile(Long id);
    Map<String, Object> getStudentDashboard(Long userId);
}