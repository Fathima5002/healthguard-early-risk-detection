package com.healthtracker.health_tracker.service;

import com.healthtracker.health_tracker.model.*;
import com.healthtracker.health_tracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HealthService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private PredictionRepository predictionRepository;

    @Autowired
    private RiskCategoryService riskCategoryService;

    @Autowired
    private MlService mlService;   // NEW — inject ML service

    @Autowired
    private UserRepository userRepository; // NEW — needed to get user details for ML

    public Prediction submitHealthData(HealthRecord record, Symptom symptom) {

        // ── Step 1: Save health record ────────────────────────
        HealthRecord savedRecord = healthRecordRepository.save(record);

        // ── Step 2: Save symptoms ─────────────────────────────
        symptom.setRecordId(savedRecord.getId());
        symptomRepository.save(symptom);

        // ── Step 3: Fetch user details ────────────────────────
        // We need age and gender from the users table for ML input
        User user = userRepository.findById(record.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ── Step 4: Build the ML request payload ──────────────
        MlRequestPayload payload = new MlRequestPayload();

        // From user profile
        payload.setAge(user.getAge());
        payload.setGender(user.getGender());

        // From health record
        payload.setHeartRate(record.getHeartRate());
        payload.setBloodPressure(record.getBloodPressure());
        payload.setBloodGlucose(record.getBloodGlucose());

        // Cholesterol — use 200 as default if user did not provide it
        payload.setCholesterol(
                record.getCholesterol() != null ? record.getCholesterol() : 200
        );

        // From symptoms
        payload.setChestPain(symptom.isChestPain());
        payload.setFatigue(symptom.isFatigue());
        payload.setDizziness(symptom.isDizziness());
        payload.setShortnessBreath(symptom.isShortnessBreath());
        payload.setHeadache(symptom.isHeadache());
        payload.setFrequentUrination(symptom.isFrequentUrination());
        payload.setBlurredVision(symptom.isBlurredVision());

        // ── Step 5: Call Flask ML API ─────────────────────────
        MlPredictionResponse mlResponse = mlService.getPrediction(payload);
        // mlResponse now contains real ML scores from your trained model

        // ── Step 6: Calculate categories and recommendation ───
        String heartCategory    = riskCategoryService.getCategory(mlResponse.getHeartRisk());
        String diabetesCategory = riskCategoryService.getCategory(mlResponse.getDiabetesRisk());
        String recommendation   = riskCategoryService.getRecommendation(heartCategory, diabetesCategory);

        // ── Step 7: Save and return prediction ────────────────
        Prediction prediction = new Prediction();
        prediction.setUserId(record.getUserId());
        prediction.setRecordId(savedRecord.getId());
        prediction.setHeartRisk(mlResponse.getHeartRisk());
        prediction.setDiabetesRisk(mlResponse.getDiabetesRisk());
        prediction.setHeartCategory(heartCategory);
        prediction.setDiabetesCategory(diabetesCategory);
        prediction.setRecommendation(recommendation);

        return predictionRepository.save(prediction);
    }

    public List<Prediction> getHistory(int userId) {
        return predictionRepository.findByUserIdOrderByPredictedAtDesc(userId);
    }
}