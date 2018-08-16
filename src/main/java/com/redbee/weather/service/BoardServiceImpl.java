package com.redbee.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redbee.weather.model.dao.IBoardDAO;
import com.redbee.weather.model.entity.Board;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BoardServiceImpl implements IBoardService {

	@Autowired
	IBoardDAO boardDAO;

	@Override
	public Flux<Board> findAll() {
		return boardDAO.findAll();
	}

	@Override
	public Mono<Board> findById(String id) {
		return boardDAO.findById(id);
	}

	@Override
	public Mono<Board> save(Board board) {
		return boardDAO.save(board);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return boardDAO.deleteById(id);
	}

	@Override
	public Flux<Board> findByUser(String userId) {
		return boardDAO.findByUser(userId);
	}

}
