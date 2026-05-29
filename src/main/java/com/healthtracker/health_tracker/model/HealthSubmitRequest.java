package com.healthtracker.health_tracker.model;

// This class is NOT an @Entity — it does NOT map to a MySQL table
// It is just a wrapper to receive the combined JSON request from the frontend
public class HealthSubmitRequest {

    private HealthRecord healthRecord;
    private Symptom symptom;

    public HealthRecord getHealthRecord() { return healthRecord; }
    public void setHealthRecord(HealthRecord healthRecord) { this.healthRecord = healthRecord; }

    public Symptom getSymptom() { return symptom; }
    public void setSymptom(Symptom symptom) { this.symptom = symptom; }
}