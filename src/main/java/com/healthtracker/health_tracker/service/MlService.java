package com.healthtracker.health_tracker.service;

import com.healthtracker.health_tracker.model.MlPredictionResponse;
import com.healthtracker.health_tracker.model.MlRequestPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MlService {

    @Autowired
    private RestTemplate restTemplate;

    // Flask ML service URL — running on port 5000
    private static final String ML_URL = "http://localhost:5000/ml/predict";

    public MlPredictionResponse getPrediction(MlRequestPayload payload) {

        // postForObject does 3 things in one line:
        // 1. Converts payload object to JSON
        // 2. Sends POST request to Flask URL
        // 3. Converts Flask's JSON response back to MlPredictionResponse object
        MlPredictionResponse response = restTemplate.postForObject(
                ML_URL,                      // URL to call
                payload,                     // object to send as JSON body
                MlPredictionResponse.class   // what class to map the response into
        );

        return response;
    }
}