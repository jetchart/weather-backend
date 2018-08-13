package com.redbee.weather.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.redbee.weather.model.entity.Location;

public interface ILocationDAO extends CrudRepository<Location, Long>{

}
