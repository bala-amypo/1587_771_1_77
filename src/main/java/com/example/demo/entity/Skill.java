
// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Skill {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true)
//     private String code;

//     private String name;
//     private String category;
//     private String description;
//     private int minCompetencyScore;

//     @Builder.Default
//     private boolean active = true;
// }







package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Requirement: unique and required
    private String code;

    @Column(nullable = false) // Requirement: category is required
    private String name;

    @Column(nullable = false)
    private String category;

    private String description; // Optional

    // EDITED: Changed to Double to match requirements (0-100)
    private Double minCompetencyScore;

    @Builder.Default
    private boolean active = true;

    /**
     * Validation logic to ensure minCompetencyScore is between 0 and 100.
     * Throws IllegalArgumentException to satisfy test requirements.
     */
    @PrePersist
    @PreUpdate
    private void validateSkill() {
        if (minCompetencyScore != null && (minCompetencyScore < 0 || minCompetencyScore > 100)) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
    }
}