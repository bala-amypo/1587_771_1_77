package com.example.demo.service;
import com.example.demo.entity.StudentProfile;
import java.util.List;
public interface StudentProfileService {
    StudentProfile createProfile(StudentProfile profile);
    StudentProfile getProfileById(Long id);
    List<StudentProfile> getAllProfiles();
}
