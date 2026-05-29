package com.healthtracker.health_tracker.repository;

import com.healthtracker.health_tracker.model.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Integer> {

    // Spring Data JPA reads this method name and automatically generates the SQL:
    // SELECT * FROM predictions WHERE user_id = ? ORDER BY predicted_at DESC
    List<Prediction> findByUserIdOrderByPredictedAtDesc(int userId);
}