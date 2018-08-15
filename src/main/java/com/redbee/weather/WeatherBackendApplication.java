package com.redbee.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@SpringBootApplication
public class WeatherBackendApplication {

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(WeatherBackendApplication.class, args);
	}
}
