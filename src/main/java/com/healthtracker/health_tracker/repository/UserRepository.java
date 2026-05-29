package com.healthtracker.health_tracker.repository;

import com.healthtracker.health_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository   // Tells Spring: "this is a repository bean — manage it"
public interface UserRepository extends JpaRepository<User, Integer> {
    // That's it. Nothing else needed right now.
}