package com.redbee.weather.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redbee.weather.model.dao.ILocationDAO;
import com.redbee.weather.model.entity.Location;

@Service
public class LocationServiceImpl implements ILocationService {

	@Autowired
	ILocationDAO locationDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Location> findAll() {
		return (List<Location>) locationDAO.findAll();
	}

	@Override
	public Location findById(Long id) {
		Optional<Location> o = locationDAO.findById(id);
		return o.get();
	}

	@Override
	public Location save(Location location) {
		return locationDAO.save(location);
	}

	@Override
	public void deleteById(Long id) {
		locationDAO.deleteById(id);
	}

	@Override
	public List<Location> findByNombreContaining(String nombre) {
		return locationDAO.findByNombreContaining(nombre);
	}

}
