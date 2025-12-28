// package com.example.demo.serviceimpl;

// import com.example.demo.entity.StudentProfile;
// import com.example.demo.repository.StudentProfileRepository;
// import com.example.demo.service.StudentProfileService; //1
// import org.springframework.stereotype.Service; //2
// import java.util.List;
// @Service
// public class StudentProfileServiceImpl implements StudentProfileService {
// {

//     private final StudentProfileRepository repo;

//     public StudentProfileServiceImpl(StudentProfileRepository repo) {
//         this.repo = repo;
//     }

//     public StudentProfile createOrUpdateProfile(StudentProfile profile) {
//         return repo.save(profile);
//     }

//     public StudentProfile getByUserId(Long userId) {
//         return repo.findByUserId(userId)
//                 .orElseThrow(() -> new RuntimeException("profile not found"));
//     }

//     public StudentProfile getProfileById(Long id) {
//         return repo.findById(id)
//                 .orElseThrow(() -> new RuntimeException("profile not found"));
//     }

//     public List<StudentProfile> getAllProfiles() {
//         return repo.findAll();
//     }
// }

package com.example.demo.serviceimpl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repo;

    public StudentProfileServiceImpl(StudentProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public StudentProfile createOrUpdateProfile(StudentProfile profile) {
        return repo.save(profile);
    }

    @Override
    public StudentProfile getByUserId(Long userId) {
        return repo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("profile not found"));
    }

    @Override
    public StudentProfile getProfileById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("profile not found"));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return repo.findAll();
    }
}
