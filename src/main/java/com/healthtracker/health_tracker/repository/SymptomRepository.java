package com.healthtracker.health_tracker.repository;

import com.healthtracker.health_tracker.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Integer> {
}