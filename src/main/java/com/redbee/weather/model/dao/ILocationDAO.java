package com.redbee.weather.model.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.redbee.weather.model.entity.Location;

import reactor.core.publisher.Flux;

public interface ILocationDAO extends ReactiveMongoRepository<Location, String>{

	public Flux<Location> findByNombreContaining(String nombre);
	
}
