package com.redbee.weather.service;

import java.util.List;

import com.redbee.weather.model.entity.Location;
import com.redbee.weather.model.entity.User;
import com.redbee.weather.model.entity.UserLocation;

public interface IUsuarioService {

	public List<User> findAll();
	public User findById(Long id);
	public User save(User usuario);
	public void deleteById(Long id);	
	public List<UserLocation> findLocationsByUser(User user);
	public UserLocation findLocationByUserAndLocation(User user, Location location);
	public void deleteByUser(User user);
	public void deleteByUserAndLocation(User user, Location location);
	public UserLocation save(UserLocation userLocation);
	
}
