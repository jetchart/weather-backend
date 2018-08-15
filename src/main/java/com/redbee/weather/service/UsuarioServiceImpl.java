package com.redbee.weather.service;

import java.util.List;

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
//	@Autowired
	IUserLocationDAO userLocationDAO;
	
	@Override
	@Transactional(readOnly=true)
	public Flux<User> findAll() {
		return usuarioDAO.findAll();
	}

	@Override
	public Mono<User> findById(Long id) {
		return usuarioDAO.findById(id);
	}

	@Override
	public Mono<User> save(User usuario) {
		return usuarioDAO.save(usuario);
	}

	@Override
	public void deleteById(Long id) {
		usuarioDAO.deleteById(id);
	}

	@Override
	public List<UserLocation> findLocationsByUser(User user) {
		return userLocationDAO.findLocationsByUser(user);
	}

	@Override
	public UserLocation findLocationByUserAndLocation(User user, Location location) {
		return userLocationDAO.findByUserAndLocation(user, location);
	}

	@Override
	public void deleteByUser(User user) {
		 userLocationDAO.deleteByUser(user);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteByUserAndLocation(User user, Location location) {
		userLocationDAO.deleteByUserAndLocation(user, location);
	}
	
	@Override
	public UserLocation save(UserLocation userLocation) {
		UserLocation userLocationNew = userLocationDAO.findByUserAndLocation(userLocation.getUser(), userLocation.getLocation());
		 if (userLocationNew == null) {
//			 return userLocationDAO.save(userLocation); 
		 } 
		 return userLocationNew;
	}

}
