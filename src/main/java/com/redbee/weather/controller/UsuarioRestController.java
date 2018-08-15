package com.redbee.weather.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.redbee.weather.model.entity.User;
import com.redbee.weather.model.entity.UserLocation;
import com.redbee.weather.service.ILocationService;
import com.redbee.weather.service.IUsuarioService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
//@CrossOrigin (origins = {"http://localhost:4200"})
@CrossOrigin ()
public class UsuarioRestController {

	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	ILocationService locationService;
		
	private static final Logger log = LoggerFactory.getLogger(UsuarioRestController.class);
	
	@GetMapping("/users")
	public Flux<User> getUsers() {
        Flux<User> users = usuarioService.findAll();
        return users;
	}

	@GetMapping("/users/{id}")
	public Mono<User> getUserById(@PathVariable String id) {
		return this.usuarioService.findById(id);
	}

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<User> create(@RequestBody User usuario) {
		usuario.setCreateAt(new Date());
		return this.usuarioService.save(usuario);
	}

	@PutMapping("/users/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<User> update(@RequestBody User usuario, @PathVariable String id) {
		Mono<User> currentUsuario = this.usuarioService.findById(id);
//		currentUsuario.setNombre(usuario.getNombre());
//		currentUsuario.setApellido(usuario.getApellido());
//		this.usuarioService.save(currentUsuario);
		return currentUsuario;
	}

	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteById(@PathVariable String id) {
		log.info("Eliminar: " + id);
		return this.usuarioService.deleteById(id);
	}
	
	@GetMapping("/users/{userId}/locations/")
	public Flux<UserLocation> findLocationsByUserId(@PathVariable String userId) {
		User user = new User();
		user.setId(userId);
		log.info("findLocationsByUserId: " + userId);
		return this.usuarioService.findLocationsByUser(userId);
	}
	
	@GetMapping("/users/{userId}/locations/{locationId}")
	public Mono<UserLocation> findLocationByUserAndLocation(@PathVariable String userId, @PathVariable String locationId) {
		User user = new User();
		user.setId(userId);
		Location location = new Location();
		location.setId(locationId);
		return this.usuarioService.findLocationByUserAndLocation(user, location);
	}
	
	@DeleteMapping("/users/{userId}/locations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserLocationsByUser(@PathVariable String userId) {
		User user = new User();
		user.setId(userId);
		this.usuarioService.deleteByUser(user);
	}
	
	@DeleteMapping("/users/locations/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteUserLocationById(@PathVariable String id) {
		log.info("deleteUserLocationById:" + id);
		return this.usuarioService.deleteUserLocationById(id);
	}
	
	@PostMapping("/users/locations")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<UserLocation> save(@RequestBody UserLocation userLocation) {
		return this.usuarioService.save(userLocation);
	}
	
	
}
