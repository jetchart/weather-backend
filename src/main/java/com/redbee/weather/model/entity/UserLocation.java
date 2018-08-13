package com.redbee.weather.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = -6292341942816977255L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@JoinColumn(name = "user_id")
	private Usuario user;
	@JoinColumn(name = "location_id")
	private Location location;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUser(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUser() {
		return user;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Location getLocation() {
		return location;
	}
		
}
