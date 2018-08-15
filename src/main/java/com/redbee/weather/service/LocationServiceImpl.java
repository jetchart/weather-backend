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

	@Override
	public List<Location> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location save(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Location> findByNombreContaining(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}}
