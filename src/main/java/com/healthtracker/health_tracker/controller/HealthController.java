package com.healthtracker.health_tracker.controller;

import com.healthtracker.health_tracker.model.HealthSubmitRequest;
import com.healthtracker.health_tracker.model.Prediction;
import com.healthtracker.health_tracker.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/health")
@CrossOrigin(origins = "*")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitHealth(@RequestBody HealthSubmitRequest request) {
        // ResponseEntity<?> means we can return either a Prediction or an error message

        try {
            Prediction result = healthService.submitHealthData(
                    request.getHealthRecord(),
                    request.getSymptom()
            );
            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            // If user not found or ML service is down — return a clean error message
            // instead of a confusing 500 stack trace
            return ResponseEntity
                    .badRequest()
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Prediction>> getHistory(@PathVariable int userId) {
        // @PathVariable tells Spring: "take the {userId} value from the URL and inject it here"
        // Example: GET /api/health/history/1 → userId = 1

        List<Prediction> history = healthService.getHistory(userId);
        return ResponseEntity.ok(history);
    }
}