package com.redbee.weather.service;

import com.redbee.weather.model.entity.Location;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILocationService {

	public Flux<Location> findAll();
	public Mono<Location> findById(String id);
	public Mono<Location> findByWoeid(String woeid);
	public Mono<Location> save(Location location);
	public void deleteById(String id);
	public Flux<Location> findByNombreContaining(String nombre);
	
}
