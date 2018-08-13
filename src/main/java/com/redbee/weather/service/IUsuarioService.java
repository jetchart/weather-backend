package com.redbee.weather.service;

import java.util.List;
import java.util.Optional;

import com.redbee.weather.model.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	public Usuario findById(Long id);
	public Usuario save(Usuario usuario);
	public void deleteById(Long id);
	
}
