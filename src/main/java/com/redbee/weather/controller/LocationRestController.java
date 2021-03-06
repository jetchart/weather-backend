package com.redbee.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.redbee.weather.model.entity.Location;
import com.redbee.weather.service.ILocationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
//@CrossOrigin (origins = {"http://localhost:4200"})
@CrossOrigin ()
@Secured("ROLE_ADMIN")
public class LocationRestController {

	@Autowired
	ILocationService locationService;
		
	@GetMapping("/locations")
	public Flux<Location> index() {
		return locationService.findAll();
	}
	
	@GetMapping("/locations/enabled")
	public Flux<Location> findByEnabledTrue() {
		return locationService.findByEnabledTrue();
	}

	@GetMapping("/locations/{id}")
	public Mono<Location> show(@PathVariable String id) {
		return this.locationService.findById(id);
	}

	@PostMapping("/locations")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Location> create(@RequestBody Location location) {
		return this.locationService.save(location);
	}

	@DeleteMapping("/locations/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable String id) {
		return this.locationService.deleteById(id);
	}
	
	@GetMapping("/locations/nombre/{nombre}")
	public Flux<Location> findLocationsByName(@PathVariable String name) {
		return this.locationService.findByNameContaining(name);
	}

}
