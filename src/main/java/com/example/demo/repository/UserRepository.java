package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Required by test suite
    boolean existsByEmail(String email);

    // Used in AuthService
    Optional<User> findByEmail(String email);
}
