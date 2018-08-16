package com.redbee.weather.service;

import com.redbee.weather.model.entity.Board;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBoardService {

	public Flux<Board> findAll();
	public Mono<Board> findById(String id);
	public Mono<Board> save(Board location);
	public Mono<Void> deleteById(String id);
	public Flux<Board> findByUser(String userId);
	
}
