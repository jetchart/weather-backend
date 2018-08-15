package com.redbee.weather.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.redbee.weather.model.entity.Location;

public interface ILocationDAO {
//public interface ILocationDAO extends CrudRepository<Location, Long>{

	public List<Location> findByNombreContaining(String nombre);
	
}
