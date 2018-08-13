package com.redbee.weather.service;

import java.util.List;
import java.util.Optional;

import com.redbee.weather.model.entity.Location;

public interface ILocationService {

	public List<Location> findAll();
	public Location findById(Long id);
	public Location save(Location location);
	public void deleteById(Long id);
	
}
