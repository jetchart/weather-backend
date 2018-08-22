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
	@Transactional(readOnly=false)
	public Mono<Location> save(Location location) {
		Mono<Location> existing = this.findByWoeid(location.getWoeid());
		if (existing != null && existing.block() != null) {
			return existing;
		}
		if (location.getId() == null) {
			location.setCreateAt(new Date());
			location.setEnabled(Boolean.FALSE);
		}
		location.setUpdateAt(new Date());
		return locationDAO.save(location);
	}

	@Override
	@Transactional(readOnly=false)
	public Mono<Void> deleteById(String id) {
		return locationDAO.deleteById(id);
	}

	@Override
	public Flux<Location> findByNameContaining(String name) {
		return locationDAO.findByNameContaining(name);
	}

	@Override
	public Mono<Location> findByWoeid(String woeid) {
		return locationDAO.findByWoeid(woeid);
	}

	@Override
	public Flux<Location> findByEnabledTrue() {
		return locationDAO.findByEnabledTrue();
	}

}
