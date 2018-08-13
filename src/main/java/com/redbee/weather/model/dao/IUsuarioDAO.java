package com.redbee.weather.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.redbee.weather.model.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{

}
