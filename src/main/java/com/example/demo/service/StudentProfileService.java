package com.example.demo.service;

import com.example.demo.entity.StudentProfile;
import java.util.List;

public interface StudentProfileService {

    StudentProfile create(StudentProfile profile);

    List<StudentProfile> getAll();

    StudentProfile getById(Long id);

    StudentProfile getByUserId(Long userId);
}
