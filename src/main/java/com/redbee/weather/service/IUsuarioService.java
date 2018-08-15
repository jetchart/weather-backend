package com.redbee.weather.service;

import java.util.List;

import com.redbee.weather.model.entity.Location;
import com.redbee.weather.model.entity.User;
import com.redbee.weather.model.entity.UserLocation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUsuarioService {

	public Flux<User> findAll();
	public Mono<User> findById(Long id);
	public Mono<User> save(User usuario);
	public void deleteById(Long id);	
	public List<UserLocation> findLocationsByUser(User user);
	public UserLocation findLocationByUserAndLocation(User user, Location location);
	public void deleteByUser(User user);
	public void deleteByUserAndLocation(User user, Location location);
	public UserLocation save(UserLocation userLocation);
	
}
