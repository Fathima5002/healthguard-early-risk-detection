package com.healthtracker.health_tracker.service;

import org.springframework.stereotype.Service;

@Service
public class RiskCategoryService {

    // Takes a risk score (0.0 to 1.0) and returns "LOW", "MODERATE", or "HIGH"
    public String getCategory(double riskScore) {
        if (riskScore <= 0.30) {
            return "LOW";
        } else if (riskScore <= 0.70) {
            return "MODERATE";
        } else {
            return "HIGH";
        }
    }

    // Takes a category and returns the recommendation message
    public String getRecommendation(String heartCategory, String diabetesCategory) {

        // If either risk is HIGH, give the most urgent recommendation
        if (heartCategory.equals("HIGH") || diabetesCategory.equals("HIGH")) {
            return "High risk level detected. Please consult a healthcare professional for proper evaluation.";
        }

        // If either is MODERATE, give a moderate recommendation
        if (heartCategory.equals("MODERATE") || diabetesCategory.equals("MODERATE")) {
            return "Moderate risk level detected. Monitor your readings regularly and consider lifestyle improvements.";
        }

        // Both are LOW
        return "Low risk level detected. Maintain healthy habits and continue regular monitoring.";
    }
}