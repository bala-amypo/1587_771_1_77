package com.example.demo.service;

import com.example.demo.entity.StudentProfile;

import java.util.List;

public interface StudentProfileService {

    StudentProfile create(StudentProfile profile);

    StudentProfile getById(Long id);

    StudentProfile getByUserId(Long userId);

    List<StudentProfile> getAll();
}
