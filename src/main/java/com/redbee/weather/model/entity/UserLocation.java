package com.redbee.weather.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserLocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1399394809972858735L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne	
    @JoinColumn(name = "user_id")
	private User user;
    @ManyToOne
    @JoinColumn(name = "location_id")
	private Location location;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Location getLocation() {
		return location;
	}
		
}
