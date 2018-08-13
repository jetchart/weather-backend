package com.redbee.weather.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.redbee.weather.model.entity.UserLocation;

public interface IUserLocation extends CrudRepository<UserLocation, Long>{

	public List<UserLocation> findLocationsByUserId(Long userId);
	
}
