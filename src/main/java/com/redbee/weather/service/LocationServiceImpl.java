package com.redbee.weather.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redbee.weather.model.dao.ILocationDAO;
import com.redbee.weather.model.entity.Location;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LocationServiceImpl implements ILocationService {

	@Autowired
	ILocationDAO locationDAO;

	@Override
	public Flux<Location> findAll() {
		return locationDAO.findAll();
	}

	@Override
	public Mono<Location> findById(String id) {
		return locationDAO.findById(id);
	}

	@Override
	public Mono<Location> save(Location location) {
		if (location.getId() != null) {
			location.setUpdateAt(new Date());
		}
		location.setCreateAt(new Date());
		return locationDAO.save(location);
	}

	@Override
	public void deleteById(String id) {
		locationDAO.deleteById(id);
	}

	@Override
	public Flux<Location> findByNombreContaining(String nombre) {
		return locationDAO.findByNombreContaining(nombre);
	}

}
