package com.redbee.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.redbee.weather.model.entity.Board;
import com.redbee.weather.service.IBoardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
//@CrossOrigin (origins = {"http://localhost:4200"})
@CrossOrigin ()
@Secured("ROLE_ADMIN")
public class BoardRestController {

	@Autowired
	IBoardService boardService;

	@GetMapping("/boards")
	public Flux<Board> index() {
		return boardService.findAll();
	}

	@GetMapping("/boards/{id}")
	public Mono<Board> show(@PathVariable String id) {
		return this.boardService.findById(id);
	}

	@PostMapping("/boards")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Board> create(@RequestBody Board board) {
		return this.boardService.save(board);
	}

	@PutMapping("/boards/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Board> update(@RequestBody Board board, @PathVariable String id) {
		Mono<Board> currentBoard = this.boardService.findById(id);
//		currentBoard.setNombre(board.getNombre());
//		currentBoard.setWoeid(board.getWoeid());
//		this.boardService.save(currentBoard);
		return currentBoard;
	}

	@DeleteMapping("/boards/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable String id) {
		return this.boardService.deleteById(id);
	}
	
	@GetMapping("/users/{userId}/boards")
	public Flux<Board> findByUser(@PathVariable String userId) {
		return this.boardService.findByUsername(userId);
	}
}
