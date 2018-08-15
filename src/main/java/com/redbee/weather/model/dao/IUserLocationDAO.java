package com.redbee.weather.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.redbee.weather.model.entity.Location;
import com.redbee.weather.model.entity.User;
import com.redbee.weather.model.entity.UserLocation;

public interface IUserLocationDAO {
//public interface IUserLocationDAO extends CrudRepository<UserLocation, Long>{

//	@Query("SELECT ul FROM UserLocation ul WHERE ul.user = :user")
	public List<UserLocation> findLocationsByUser(User user);
	public UserLocation findByUserAndLocation(User user, Location location);
	public void deleteByUser(User user);
	public void deleteByUserAndLocation(User user, Location location);
	
}
