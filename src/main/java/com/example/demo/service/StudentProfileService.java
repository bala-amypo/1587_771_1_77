package com.example.demo.service;

import com.example.demo.entity.StudentProfile;

import java.util.List;

public interface StudentProfileService {

    StudentProfile saveProfile(StudentProfile profile);

    List<StudentProfile> getAllProfiles();

    List<StudentProfile> getProfilesByUserId(Long userId);
}
