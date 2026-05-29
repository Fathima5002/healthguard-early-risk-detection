package com.healthtracker.health_tracker.controller;

import com.healthtracker.health_tracker.model.User;
import com.healthtracker.health_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController                // This class handles HTTP requests and sends back JSON responses
@RequestMapping("/api")        // All URLs in this class start with /api
@CrossOrigin(origins = "*")   // Allows frontend HTML files to call this API (we need this later)
public class UserController {

    @Autowired
    private UserService userService;   // Spring injects UserService automatically

    @PostMapping("/register")          // This method runs when POST /api/register is called
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // @RequestBody tells Spring: "take the JSON from the request and convert it into a User object"

        User savedUser = userService.registerUser(user);
        // savedUser now has the id that MySQL generated (e.g. id = 1)

        return ResponseEntity.ok(savedUser);
        // ResponseEntity.ok() sends back HTTP 200 status + the savedUser object as JSON
    }
}