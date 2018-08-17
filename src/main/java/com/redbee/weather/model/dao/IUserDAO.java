package com.redbee.weather.model.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.redbee.weather.model.entity.User;

import reactor.core.publisher.Mono;

public interface IUserDAO extends ReactiveMongoRepository<User, String>{

	public Mono<Void> deleteById(String id);	
	
}
