package com.healthtracker.health_tracker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "health_records")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "heart_rate", nullable = false)
    private int heartRate;

    @Column(name = "blood_pressure", nullable = false)
    private int bloodPressure;

    @Column(name = "blood_glucose", nullable = false)
    private int bloodGlucose;

    @Column(name = "cholesterol")
    private Integer cholesterol;   // Integer (not int) because it is optional — can be null

    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;

    @PrePersist
    public void prePersist() {
        this.recordedAt = LocalDateTime.now();
    }

    // ── Getters and Setters ──────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getHeartRate() { return heartRate; }
    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }

    public int getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(int bloodPressure) { this.bloodPressure = bloodPressure; }

    public int getBloodGlucose() { return bloodGlucose; }
    public void setBloodGlucose(int bloodGlucose) { this.bloodGlucose = bloodGlucose; }

    public Integer getCholesterol() { return cholesterol; }
    public void setCholesterol(Integer cholesterol) { this.cholesterol = cholesterol; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
}