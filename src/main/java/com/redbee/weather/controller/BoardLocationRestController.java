package com.redbee.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.redbee.weather.model.entity.Board;
import com.redbee.weather.model.entity.BoardLocation;
import com.redbee.weather.model.entity.Location;
import com.redbee.weather.service.IBoardLocationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
//@CrossOrigin (origins = {"http://localhost:4200"})
@CrossOrigin ()
public class BoardLocationRestController {

	private static final Logger log = LoggerFactory.getLogger(BoardLocationRestController.class);
	
	@Autowired
	IBoardLocationService boardLocationService;

	@GetMapping("/boards/{boardId}/locations")
	public Flux<BoardLocation> findByBoardLocations(@PathVariable String boardId) {
		log.info("findByBoards: " + boardId);
		return this.boardLocationService.findByBoard(boardId);
	}
	
	@GetMapping("/boards/{board}/locations/{location}")
	public Mono<BoardLocation> findByBoardAndLocation(@PathVariable Board board, Location location) {
		return this.boardLocationService.findByBoardAndLocation(board, location);
	}
	
	@GetMapping("/users/{userId}/boards/locations")
	public Flux<BoardLocation> findByUser(@PathVariable String userId) {
		return this.boardLocationService.findByUser(userId);
	}
	
	@PostMapping("/boards/locations")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<BoardLocation> create(@RequestBody BoardLocation boardLocation) {
		return this.boardLocationService.save(boardLocation);
	}

	@DeleteMapping("/boards/locations/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable String id) {
		return this.boardLocationService.deleteById(id);
	}
	

}
