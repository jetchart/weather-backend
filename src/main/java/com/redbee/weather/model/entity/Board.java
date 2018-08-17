package com.redbee.weather.model.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="boards")
public class Board implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2290495264777328219L;
	
	@Id
	private String id;
	@DBRef
	private User user;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
		
}
