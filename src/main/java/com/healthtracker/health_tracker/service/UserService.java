package com.healthtracker.health_tracker.service;

import com.healthtracker.health_tracker.model.User;
import com.healthtracker.health_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service   // Tells Spring: "this is a service bean — manage it"
public class UserService {

    @Autowired   // Spring automatically injects UserRepository here — you don't create it manually
    private UserRepository userRepository;

    public User registerUser(User user) {
        // This one line saves the user object to MySQL and returns the saved object with the generated id
        return userRepository.save(user);
    }
}