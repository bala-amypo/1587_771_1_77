package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    private boolean active = true;

    // ---------- Getters & Setters ----------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // ---------- Builder ----------

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final Skill s = new Skill();

        public Builder id(Long id) { s.setId(id); return this; }
        public Builder code(String c) { s.setCode(c); return this; }
        public Builder name(String n) { s.setName(n); return this; }
        public Builder active(boolean a) { s.setActive(a); return this; }

        public Skill build() { return s; }
    }
}
