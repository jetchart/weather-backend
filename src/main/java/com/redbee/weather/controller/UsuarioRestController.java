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
import com.redbee.weather.model.entity.User;
import com.redbee.weather.model.entity.UserLocation;
import com.redbee.weather.service.IUsuarioService;

@RestController
@RequestMapping("/api")
//@CrossOrigin (origins = {"http://localhost:4200"})
@CrossOrigin ()
public class UsuarioRestController {

	@Autowired
	IUsuarioService usuarioService;
		
	@GetMapping("/users")
	public List<User> getUsers() {
		return usuarioService.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable Long id) {
		return this.usuarioService.findById(id);
	}

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User usuario) {
		usuario.setCreateAt(new Date());
		this.usuarioService.save(usuario);
		return usuario;
	}

	@PutMapping("/users/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public User update(@RequestBody User usuario, @PathVariable Long id) {
		User currentUsuario = this.usuarioService.findById(id);
		currentUsuario.setNombre(usuario.getNombre());
		currentUsuario.setApellido(usuario.getApellido());
		this.usuarioService.save(currentUsuario);
		return currentUsuario;
	}

	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.usuarioService.deleteById(id);
	}
	
	@GetMapping("/users/{userId}/locations/")
	public List<UserLocation> findLocationsByUserId(@PathVariable Long userId) {
		User user = new User();
		user.setId(userId);
		return this.usuarioService.findLocationsByUser(user);
	}
	
	@GetMapping("/users/{userId}/locations/{locationId}")
	public UserLocation findLocationByUserAndLocation(@PathVariable Long userId, @PathVariable Long locationId) {
		User user = new User();
		user.setId(userId);
		Location location = new Location();
		location.setId(locationId);
		return this.usuarioService.findLocationByUserAndLocation(user, location);
	}
	
	@DeleteMapping("/users/{userId}/locations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserLocationsByUser(@PathVariable Long userId) {
		User user = new User();
		user.setId(userId);
		this.usuarioService.deleteByUser(user);
	}
	
	@DeleteMapping("/users/{userId}/locations/{locationId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteByUserAndLocation(@PathVariable Long userId, @PathVariable Long locationId) {
		User user = new User();
		user.setId(userId);
		Location location = new Location();
		location.setId(locationId);
		System.out.println(userId + " " + locationId);
		UserLocation userLocation = this.usuarioService.findLocationByUserAndLocation(user, location);
		this.usuarioService.deleteByUserAndLocation(userLocation.getUser(), userLocation.getLocation());
	}
	
	@PostMapping("/users/locations")
	@ResponseStatus(HttpStatus.CREATED)
	public UserLocation save(@RequestBody UserLocation userLocation) {
		return this.usuarioService.save(userLocation);
	}
	
	
}
