package com.healthtracker.health_tracker.model;

// This class maps to the JSON Flask returns:
// { "heartRisk": 0.31, "diabetesRisk": 0.56 }
// It is NOT an @Entity — it does NOT map to a MySQL table
public class MlPredictionResponse {

    private double heartRisk;
    private double diabetesRisk;

    // Getters and Setters
    public double getHeartRisk() { return heartRisk; }
    public void setHeartRisk(double heartRisk) { this.heartRisk = heartRisk; }

    public double getDiabetesRisk() { return diabetesRisk; }
    public void setDiabetesRisk(double diabetesRisk) { this.diabetesRisk = diabetesRisk; }
}