package com.redbee.weather.model.entity;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User {
	
	@Id
	private String id;

	private String username;

	private String nombre;

	private String apellido;

	private Date createAt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
		
}
