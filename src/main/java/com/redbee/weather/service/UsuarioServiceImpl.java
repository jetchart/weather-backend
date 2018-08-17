package com.redbee.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redbee.weather.model.dao.IUserDAO;
import com.redbee.weather.model.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	IUserDAO usuarioDAO;
	
	@Override
	@Transactional(readOnly=true)
	public Flux<User> findAll() {
		return usuarioDAO.findAll();
	}

	@Override
	public Mono<User> findById(String id) {
		return usuarioDAO.findById(id);
	}

	@Override
	public Mono<User> save(User usuario) {
		return usuarioDAO.save(usuario);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return usuarioDAO.deleteById(id);
	}

}
