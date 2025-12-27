
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

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    private String description;

    // Requirement: Must be Double to handle 0-100 range precisely
    private Double minCompetencyScore;

    @Builder.Default
    private boolean active = true;

    @PrePersist
    @PreUpdate
    private void validate() {
        if (minCompetencyScore != null && (minCompetencyScore < 0 || minCompetencyScore > 100)) {
            // Exact requirement: "Score must be between 0 and 100"
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
    }
}