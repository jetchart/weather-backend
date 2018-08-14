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

@RestController
@RequestMapping("/api")
//@CrossOrigin (origins = {"http://localhost:4200"})
@CrossOrigin ()
public class LocationRestController {

	@Autowired
	ILocationService locationService;
		
	@GetMapping("/locations")
	public List<Location> index() {
		return locationService.findAll();
	}

	@GetMapping("/locations/{id}")
	public Location show(@PathVariable Long id) {
		return this.locationService.findById(id);
	}

	@PostMapping("/locations")
	@ResponseStatus(HttpStatus.CREATED)
	public Location create(@RequestBody Location location) {
		location.setCreateAt(new Date());
		this.locationService.save(location);
		return location;
	}

	@PutMapping("/locations/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Location update(@RequestBody Location location, @PathVariable Long id) {
		Location currentLocation = this.locationService.findById(id);
		currentLocation.setNombre(location.getNombre());
		currentLocation.setWoeid(location.getWoeid());
		this.locationService.save(currentLocation);
		return currentLocation;
	}

	@DeleteMapping("/locations/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.locationService.deleteById(id);
	}
	
	@GetMapping("/locations/nombre/{nombre}")
	public List<Location> findLocationsByName(@PathVariable String nombre) {
		return this.locationService.findByNombre(nombre);
	}
}
