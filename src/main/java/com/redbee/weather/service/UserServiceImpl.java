package com.redbee.weather.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redbee.weather.model.dao.IBoardDAO;
import com.redbee.weather.model.dao.IBoardLocationDAO;
import com.redbee.weather.model.dao.IUserDAO;
import com.redbee.weather.model.entity.Board;
import com.redbee.weather.model.entity.BoardLocation;
import com.redbee.weather.model.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDAO usuarioDAO;
	@Autowired
	IBoardLocationDAO boardLocationDAO;
	@Autowired
	IBoardDAO boardDAO;
	
	@Override
	public Flux<User> findAll() {
		return usuarioDAO.findAll();
	}

	@Override
	public Mono<User> findById(String id) {
		return usuarioDAO.findById(id);
	}

	@Override
	@Transactional(readOnly=false)
	public Mono<User> save(User user) {
		if (user.getId() == null) {
			Mono<User> existing = this.findByUsername(user.getUsername());
			if (existing != null && existing.block() != null) {
				return existing;
			}
			user.setCreateAt(new Date());
		}
		return usuarioDAO.save(user);
	}

	@Override
	@Transactional(readOnly=false)
	public Mono<Void> deleteById(String id) {
		//First: Delete associated BoardLocation 
		Flux<BoardLocation> boardLocationsFlux = boardLocationDAO.findByUser(id);
		if (boardLocationsFlux.collectList() != null) {
			List<BoardLocation> boardLocations = boardLocationsFlux.collectList().block();
			if (boardLocations != null) {
				boardLocationDAO.deleteAll(boardLocations);
			}
		}
		//Second: Delete associated Board 
		Flux<Board> boardFlux = boardDAO.findByUser(id);
		if (boardFlux.collectList() != null) {
			List<Board> board = boardFlux.collectList().block();
			if (board != null) {
				boardDAO.deleteAll(board);
			}
		}
		//Finally: Delete User
		return usuarioDAO.deleteById(id);
	}

	@Override
	public Mono<User> findByUsername(String username) {
		return usuarioDAO.findByUsername(username);
	}

}
