package com.healthtracker.health_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HealthTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthTrackerApplication.class, args);
	}

	@Bean
	// @Bean tells Spring: "create one instance of RestTemplate
	// and make it available for @Autowired anywhere in the project"
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}