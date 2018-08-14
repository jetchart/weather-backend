package com.redbee.weather.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2731311784563812187L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nombre;
	@Column
	private String woeid;
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getWoeid() {
		return woeid;
	}
	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
		
}
