package com.test.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class Sensor {
	@Id
	private Integer sensorId;
	
	private String countryName;
	private String cityName;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Metrics> metrics;
	
	private Double averageHumidity;
	private Double averageTemperature;
	
	public Sensor() {
		// TODO Auto-generated constructor stub
	}
	

	public Sensor(Integer sensorId, String countryName, String cityName, List<Metrics> metrics, Double averageHumidity,
			Double averageTemperature) {
		super();
		this.sensorId = sensorId;
		this.countryName = countryName;
		this.cityName = cityName;
		this.metrics = metrics;
		this.averageHumidity = averageHumidity;
		this.averageTemperature = averageTemperature;
	}




	public Integer getSensorId() {
		return sensorId;
	}


	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	
	public List<Metrics> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<Metrics> metrics) {
		this.metrics = metrics;
	}

	public Double getAverageHumidity() {
		return averageHumidity;
	}


	public void setAverageHumidity(Double averageHumidity) {
		this.averageHumidity = averageHumidity;
	}


	public Double getAverageTemperature() {
		return averageTemperature;
	}


	public void setAverageTemperature(Double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}


	@Override
	public String toString() {
		return "Sensor [sensorId=" + sensorId + ", countryName=" + countryName + ", cityName=" + cityName + ", metrics="
				+ metrics + ", averageHumidity=" + averageHumidity + ", averageTemperature=" + averageTemperature + "]";
	}

	
	
	
}
