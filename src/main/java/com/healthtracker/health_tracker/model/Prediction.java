package com.healthtracker.health_tracker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "record_id", nullable = false)
    private int recordId;

    @Column(name = "heart_risk", nullable = false)
    private double heartRisk;       // e.g. 0.72 means 72%

    @Column(name = "diabetes_risk", nullable = false)
    private double diabetesRisk;

    @Column(name = "heart_category", nullable = false)
    private String heartCategory;   // "LOW", "MODERATE", "HIGH"

    @Column(name = "diabetes_category", nullable = false)
    private String diabetesCategory;

    @Column(name = "recommendation")
    private String recommendation;

    @Column(name = "predicted_at")
    private LocalDateTime predictedAt;

    @PrePersist
    public void prePersist() {
        this.predictedAt = LocalDateTime.now();
    }

    // ── Getters and Setters ──────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }

    public double getHeartRisk() { return heartRisk; }
    public void setHeartRisk(double heartRisk) { this.heartRisk = heartRisk; }

    public double getDiabetesRisk() { return diabetesRisk; }
    public void setDiabetesRisk(double diabetesRisk) { this.diabetesRisk = diabetesRisk; }

    public String getHeartCategory() { return heartCategory; }
    public void setHeartCategory(String heartCategory) { this.heartCategory = heartCategory; }

    public String getDiabetesCategory() { return diabetesCategory; }
    public void setDiabetesCategory(String diabetesCategory) { this.diabetesCategory = diabetesCategory; }

    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }

    public LocalDateTime getPredictedAt() { return predictedAt; }
    public void setPredictedAt(LocalDateTime predictedAt) { this.predictedAt = predictedAt; }
}