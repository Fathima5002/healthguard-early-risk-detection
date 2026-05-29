package com.healthtracker.health_tracker.model;

// This is what we send to Flask as JSON
// Flask reads these fields by name — names must match exactly
public class MlRequestPayload {

    private int age;
    private String gender;
    private int heartRate;
    private int bloodPressure;
    private int bloodGlucose;
    private int cholesterol;
    private boolean chestPain;
    private boolean fatigue;
    private boolean dizziness;
    private boolean shortnessBreath;
    private boolean headache;
    private boolean frequentUrination;
    private boolean blurredVision;

    // Getters and Setters
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getHeartRate() { return heartRate; }
    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }

    public int getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(int bloodPressure) { this.bloodPressure = bloodPressure; }

    public int getBloodGlucose() { return bloodGlucose; }
    public void setBloodGlucose(int bloodGlucose) { this.bloodGlucose = bloodGlucose; }

    public int getCholesterol() { return cholesterol; }
    public void setCholesterol(int cholesterol) { this.cholesterol = cholesterol; }

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