package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "skills",
    uniqueConstraints = @UniqueConstraint(columnNames = "skillName")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String skillName;

    @Column(nullable = false)
    private String category;

    @Column
    private String description;

    @Column(nullable = false)
    private Double minCompetencyScore;

    @Column(nullable = false)
    private Boolean active;

    @PrePersist
    @PreUpdate
    public void validate() {
        if (minCompetencyScore == null ||
                minCompetencyScore < 0 || minCompetencyScore > 100) {
            throw new IllegalArgumentException(
                "minCompetencyScore must be between 0 and 100"
            );
        }

        if (active == null) active = true;
    }
}
