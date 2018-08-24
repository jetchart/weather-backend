package com.redbee.weather.service;

import com.redbee.weather.model.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {

	public Flux<User> findAll();
	public Mono<User> findById(String id);
	public Mono<User> save(User user);
	public Mono<Void> deleteById(String id);
	public Mono<User> findByUsername(String username);
	
}
