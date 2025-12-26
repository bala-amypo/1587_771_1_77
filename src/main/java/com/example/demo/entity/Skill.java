// src/main/java/com/example/demo/entity/Skill.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String name; // This provides the getName() method via @Data
    @Builder.Default
    private boolean active = true;
}