package com.redbee.weather.model.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.redbee.weather.model.entity.User;

public interface IUsuarioDAO extends ReactiveMongoRepository<User, Long>{

}
