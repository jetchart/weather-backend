package com.redbee.weather.model.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.redbee.weather.model.entity.Board;

import reactor.core.publisher.Flux;

public interface IBoardDAO extends ReactiveMongoRepository<Board, String>{

	@Query("{ 'user.id' : ?0 }")
	public Flux<Board> findByUser(String userId);
	
}
