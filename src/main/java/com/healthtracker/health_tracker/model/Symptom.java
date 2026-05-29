package com.healthtracker.health_tracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "symptoms")
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "record_id", nullable = false)
    private int recordId;   // links to health_records.id

    @Column(name = "chest_pain")
    private boolean chestPain;

    @Column(name = "fatigue")
    private boolean fatigue;

    @Column(name = "dizziness")
    private boolean dizziness;

    @Column(name = "shortness_breath")
    private boolean shortnessBreath;

    @Column(name = "headache")
    private boolean headache;

    @Column(name = "frequent_urination")
    private boolean frequentUrination;

    @Column(name = "blurred_vision")
    private boolean blurredVision;

    // ── Getters and Setters ──────────────────────────────

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }

    public boolean isChestPain() { return chestPain; }
    public void setChestPain(boolean chestPain) { this.chestPain = chestPain; }

    public boolean isFatigue() { return fatigue; }
    public void setFatigue(boolean fatigue) { this.fatigue = fatigue; }

    public boolean isDizziness() { return dizziness; }
    public void setDizziness(boolean dizziness) { this.dizziness = dizziness; }

    public boolean isShortnessBreath() { return shortnessBreath; }
    public void setShortnessBreath(boolean shortnessBreath) { this.shortnessBreath = shortnessBreath; }

    public boolean isHeadache() { return headache; }
    public void setHeadache(boolean headache) { this.headache = headache; }

    public boolean isFrequentUrination() { return frequentUrination; }
    public void setFrequentUrination(boolean frequentUrination) { this.frequentUrination = frequentUrination; }

    public boolean isBlurredVision() { return blurredVision; }
    public void setBlurredVision(boolean blurredVision) { this.blurredVision = blurredVision; }
}