package com.healthtracker.health_tracker.repository;

import com.healthtracker.health_tracker.model.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {
}