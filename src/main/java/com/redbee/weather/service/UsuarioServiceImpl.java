package com.redbee.weather.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redbee.weather.model.dao.IUserLocationDAO;
import com.redbee.weather.model.dao.IUsuarioDAO;
import com.redbee.weather.model.entity.Location;
import com.redbee.weather.model.entity.User;
import com.redbee.weather.model.entity.UserLocation;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	IUsuarioDAO usuarioDAO;
	@Autowired
	IUserLocationDAO userLocationDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return (List<User>) usuarioDAO.findAll();
	}

	@Override
	public User findById(Long id) {
		Optional<User> o = usuarioDAO.findById(id);
		return o.get();
	}

	@Override
	public User save(User usuario) {
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
		return userLocationDAO.findLocationByUserAndLocation(user, location);
	}

	@Override
	public void deleteByUser(User user) {
		 userLocationDAO.deleteByUser(user);
	}

	@Override
	public void deleteByUserAndLocation(User user, Location location) {
		userLocationDAO.deleteByUserAndLocation(user, location);
	}
	
	@Override
	public UserLocation save(UserLocation userLocation) {
		UserLocation userLocationNew = userLocationDAO.findLocationByUserAndLocation(userLocation.getUser(), userLocation.getLocation());
		 if (userLocationNew == null) {
			 return userLocationDAO.save(userLocation); 
		 } 
		 return userLocationNew;
	}

}
