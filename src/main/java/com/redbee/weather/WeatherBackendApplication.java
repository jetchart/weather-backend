package com.redbee.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherBackendApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WeatherBackendApplication.class, args);
	}
}
