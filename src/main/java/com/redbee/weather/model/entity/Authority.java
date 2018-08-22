package com.redbee.weather.model.entity;

import java.io.Serializable;

public class Authority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8051093776564592795L;
	
	private String authority;
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getAuthority() {
		return authority;
	}
		
}
