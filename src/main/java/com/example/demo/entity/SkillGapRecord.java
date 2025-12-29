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
// public class SkillGapRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private StudentProfile studentProfile;

//     @ManyToOne
//     private Skill skill;

//     private Double currentScore;
//     private Double targetScore;
//     private Double gapScore;

//     private Instant calculatedAt = Instant.now();
// }



package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class SkillGapRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private StudentProfile studentProfile;

    @ManyToOne(optional = false)
    private Skill skill;

    @Column(nullable = false)
    private Double currentScore;

    @Column(nullable = false)
    private Double targetScore;

    @Column(nullable = false)
    private Double gapScore; // targetScore - currentScore

    @Builder.Default
    private Instant calculatedAt = Instant.now();
}