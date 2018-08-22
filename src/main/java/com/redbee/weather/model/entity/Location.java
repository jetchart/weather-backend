package com.redbee.weather.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="locations")
public class Location implements Serializable {

	/**
	 * 	 */
	private static final long serialVersionUID = -2731311784563812187L;
	
	@Id
	private String id;
	private String name;
	private String woeid;
	private String pubDate;
	private String chill ;
	private String humidity;
	private String visibility;
	private String temperature;
	private String text;
	private List<Forecast> forecasts;
	private Date createAt;
	private Date updateAt;
	private Boolean enabled;
	
	public final static String fahrenheit_code = "F";
	public final static Integer celsius_diff = 32;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getChill() {
		return chill;
	}
	public void setChill(String chill) {
		this.chill = chill;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the forecast
	 */
	public List<Forecast> getForecasts() {
		return forecasts;
	}
	/**
	 * @param forecast the forecast to set
	 */
	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
		
}
