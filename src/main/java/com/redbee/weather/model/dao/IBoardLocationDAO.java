package com.redbee.weather.model.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.redbee.weather.model.entity.Board;
import com.redbee.weather.model.entity.BoardLocation;
import com.redbee.weather.model.entity.Location;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IBoardLocationDAO extends ReactiveMongoRepository<BoardLocation, String>{

	@Query("{ 'board.id' : ?0 }")
	public Flux<BoardLocation> findByBoard(String boardId);
	@Query("{ 'board.id' : ?0 } , { 'location.id' : ?1 }")
	public Mono<BoardLocation> findByBoardAndLocation(Board board, Location location);
	@Query("{ 'board.user.username' : ?0 }")
	public Flux<BoardLocation> findByUsername(String username);
	@Query("{ 'board.user.id' : ?0 }")
	public Flux<BoardLocation> findByUser(String userId);
	public Mono<Void> deleteById(String id);

}
