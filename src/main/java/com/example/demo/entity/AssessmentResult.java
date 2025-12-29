
// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.Instant;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class AssessmentResult {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private StudentProfile studentProfile;

//     @ManyToOne
//     private Skill skill;

//     private String assessmentId;

//     private Double score;

  
//     @Builder.Default
//     private Double maxScore = 100.0;

//     @Builder.Default
//     private Instant attemptedAt = Instant.now();
// }




package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class AssessmentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private StudentProfile studentProfile;

    @ManyToOne(optional = false)
    private Skill skill;

    @Column(nullable = false)
    private String assessmentId;

    @Column(nullable = false)
    private Double score; // scoreObtained

    @Builder.Default
    private Double maxScore = 100.0;

    @Builder.Default
    private Instant attemptedAt = Instant.now();
}