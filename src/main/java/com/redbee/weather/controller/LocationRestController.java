package com.redbee.weather.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class LocationRestController {

	@Autowired
	ILocationService locationService;
		
	@GetMapping("/locations")
	public Flux<Location> index() {
		return locationService.findAll();
	}

	@GetMapping("/locations/{id}")
	public Mono<Location> show(@PathVariable String id) {
		return this.locationService.findById(id);
	}

	@PostMapping("/locations")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Location> create(@RequestBody Location location) {
		location.setCreateAt(new Date());
		return this.locationService.save(location);
	}

	@PutMapping("/locations/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Location> update(@RequestBody Location location, @PathVariable String id) {
		Mono<Location> currentLocation = this.locationService.findById(id);
//		currentLocation.setNombre(location.getNombre());
//		currentLocation.setWoeid(location.getWoeid());
//		this.locationService.save(currentLocation);
		return currentLocation;
	}

	@DeleteMapping("/locations/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		this.locationService.deleteById(id);
	}
	
	@GetMapping("/locations/nombre/{nombre}")
	public Flux<Location> findLocationsByName(@PathVariable String nombre) {
		return this.locationService.findByNombreContaining(nombre);
	}
}
