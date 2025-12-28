
package com.example.demo.repository;

import com.example.demo.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<Skill> findByCode(String code);

    List<Skill> findByActiveTrue();
}
