package com.redbee.weather.service;

import com.redbee.weather.model.entity.Location;
import com.redbee.weather.model.entity.User;
import com.redbee.weather.model.entity.UserLocation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUsuarioService {

	public Flux<User> findAll();
	public Mono<User> findById(String id);
	public Mono<User> save(User usuario);
	public Mono<Void> deleteById(String id);	
	public Flux<UserLocation> findLocationsByUser(String userId);
	public Mono<UserLocation> findLocationByUserAndLocation(User user, Location location);
	public void deleteByUser(User user);
	public Mono<UserLocation> save(UserLocation userLocation);
	public Mono<Void> deleteUserLocationById(String id);
	
}
