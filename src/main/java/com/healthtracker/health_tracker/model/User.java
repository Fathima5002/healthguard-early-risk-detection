package com.healthtracker.health_tracker.model;

import jakarta.persistence.*;
        import java.time.LocalDateTime;

@Entity                          // Tells JPA: "this class maps to a MySQL table"
@Table(name = "users")          // Tells JPA: "the table name is users"
public class User {

    @Id                                                    // This is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Auto-increment — MySQL sets this
    private int id;

    @Column(name = "name", nullable = false)    // maps to the 'name' column, cannot be null
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "family_history", nullable = false)
    private boolean familyHistory;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist   // This method runs automatically BEFORE saving to DB
    public void prePersist() {
        this.createdAt = LocalDateTime.now();  // Sets current time automatically
    }

    // ── Getters and Setters ──────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public boolean isFamilyHistory() { return familyHistory; }
    public void setFamilyHistory(boolean familyHistory) { this.familyHistory = familyHistory; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}