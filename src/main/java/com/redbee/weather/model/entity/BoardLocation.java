package com.redbee.weather.model.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="boardLocations")
public class BoardLocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1399394809972858735L;
	
	@Id
	private String id;
	@DBRef
	private Board board;
	@DBRef
	private Location location;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Board getBoard() {
		return board;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Location getLocation() {
		return location;
	}
		
}
