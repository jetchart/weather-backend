package com.redbee.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redbee.weather.model.dao.IUserLocationDAO;
import com.redbee.weather.model.dao.IUsuarioDAO;
import com.redbee.weather.model.entity.Location;
import com.redbee.weather.model.entity.User;
import com.redbee.weather.model.entity.UserLocation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	IUsuarioDAO usuarioDAO;
	@Autowired
	IUserLocationDAO userLocationDAO;
	
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

	@Override
	public Flux<UserLocation> findLocationsByUser(String userId) {
		return userLocationDAO.findLocationsByUser(userId);
	}

	@Override
	public Mono<UserLocation> findLocationByUserAndLocation(User user, Location location) {
		return userLocationDAO.findByUserAndLocation(user, location);
	}

	@Override
	public void deleteByUser(User user) {
		 userLocationDAO.deleteByUser(user);
	}

	@Override
	public Mono<Void> deleteUserLocationById(String id) {
		return userLocationDAO.deleteById(id);
	}
	
	@Override
	public Mono<UserLocation> save(UserLocation userLocation) {
		Mono<UserLocation> userLocationNew = userLocationDAO.findByUserAndLocation(userLocation.getUser(), userLocation.getLocation());
		 if (userLocationNew.hasElement() != null) {
			 return userLocationDAO.save(userLocation); 
		 } 
		 return userLocationNew;
	}

}
