package com.redbee.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbee.weather.model.dao.IBoardLocationDAO;
import com.redbee.weather.model.entity.Board;
import com.redbee.weather.model.entity.BoardLocation;
import com.redbee.weather.model.entity.Location;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BoardLocationServiceImpl implements IBoardLocationService {

	@Autowired
	IBoardLocationDAO boardLocationDAO;

	@Override
	public Flux<BoardLocation> findByBoard(String boardId) {
		return boardLocationDAO.findByBoard(boardId);
	}

	@Override
	public Mono<BoardLocation> findByBoardAndLocation(Board board, Location location) {
		return boardLocationDAO.findByBoardAndLocation(board, location);
	}

	@Override
	public Flux<BoardLocation> findByUsername(String username) {
		return boardLocationDAO.findByUsername(username);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return boardLocationDAO.deleteById(id);
	}

	@Override
	public Mono<BoardLocation> save(BoardLocation boardLocation) {
		return boardLocationDAO.save(boardLocation);
	}

	@Override
	public Flux<BoardLocation> findByUser(String userId) {
		return boardLocationDAO.findByUser(userId);
	}

//	@Override
//	public void deleteByUser(Board board) {
//		boardLocationDAO.deleteByUser(board);
//	}

}
