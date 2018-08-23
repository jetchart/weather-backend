package com.redbee.weather.service;

import com.redbee.weather.model.entity.Board;
import com.redbee.weather.model.entity.BoardLocation;
import com.redbee.weather.model.entity.Location;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBoardLocationService {

	public Flux<BoardLocation> findByBoard(String boardId);
	public Mono<BoardLocation> findByBoardAndLocation(Board board, Location location);
	public Flux<BoardLocation> findByUsername(String username);
	public Flux<BoardLocation> findByUser(String userId);
	public Mono<Void> deleteById(String id);
	public Mono<BoardLocation> save(BoardLocation boardLocation);
	
}
