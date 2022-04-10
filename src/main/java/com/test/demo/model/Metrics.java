package com.test.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Metrics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer metricsId;
	
	private double temperature;
	private double humidity;
	private Date recordedAt;
	
	public static String METRIC_TEMPERATURE="temperature";
	public static String METRIC_HUMIDITY="humidity";
	
	public Metrics() {
	}
	
	public Metrics(Integer metricsId, double temperature, double humidity, Date recordedAt) {
		super();
		this.metricsId = metricsId;
		this.temperature = temperature;
		this.humidity = humidity;
		this.recordedAt = recordedAt;
	}

	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public Integer getMetricsId() {
		return metricsId;
	}

	public void setMetricsId(Integer metricsId) {
		this.metricsId = metricsId;
	}

	public Date getRecordedAt() {
		return recordedAt;
	}

	public void setRecordedAt(Date recordedAt) {
		this.recordedAt = recordedAt;
	}

	@Override
	public String toString() {
		return "Metrics [metricsId=" + metricsId + ", temperature=" + temperature + ", humidity=" + humidity
				+ ", recordedAt=" + recordedAt + "]";
	}
	
	
	
	
}
