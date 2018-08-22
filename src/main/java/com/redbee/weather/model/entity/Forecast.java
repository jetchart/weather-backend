package com.redbee.weather.model.entity;

import java.io.Serializable;

public class Forecast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6898144754843623244L;
	
	private String date;
	private String day;
	private String high;
	private String low;
	private String text;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
