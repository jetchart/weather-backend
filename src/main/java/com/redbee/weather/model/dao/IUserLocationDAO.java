package com.redbee.weather.model.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.redbee.weather.model.entity.Location;
import com.redbee.weather.model.entity.User;
import com.redbee.weather.model.entity.UserLocation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IUserLocationDAO extends ReactiveMongoRepository<UserLocation, String>{

//	@Query("SELECT ul FROM UserLocation ul WHERE ul.user = :user")
	@Query("{ 'user.id' : ?0 }")
	public Flux<UserLocation> findLocationsByUser(String userId);
	@Query("{ 'user.id' : ?0 } , { 'location.id' : ?1 }")
	public Mono<UserLocation> findByUserAndLocation(User user, Location location);
	public Mono<Void> deleteById(String id);
	public void deleteByUser(User user);
	
}
