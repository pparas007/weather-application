package com.test.demo.model;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.test.demo.util.DateHandler;

public class SensorMetricsStatistics {
	private List<Integer> sensorIdList;
	private List<String> metricsList;
	
	@JsonDeserialize(using = DateHandler.class)
	private Date dateFrom;
	@JsonDeserialize(using = DateHandler.class)
	private Date dateTo;
	
	private List<Sensor> sensorInformation;
	
	public SensorMetricsStatistics() {
	}
	
	public SensorMetricsStatistics(List<Integer> sensorIdList, List<String> metricsList, Date dateFrom, Date dateTo,
			List<Sensor> sensorInformation) {
		super();
		this.sensorIdList = sensorIdList;
		this.metricsList = metricsList;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.sensorInformation = sensorInformation;
	}



	public List<Integer> getSensorIdList() {
		return sensorIdList;
	}
	public void setSensorIdList(List<Integer> sensorIdList) {
		this.sensorIdList = sensorIdList;
	}
	public List<String> getMetricsList() {
		return metricsList;
	}
	public void setMetricsList(List<String> metricsList) {
		this.metricsList = metricsList;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public List<Sensor> getSensorInformation() {
		return sensorInformation;
	}

	public void setSensorInformation(List<Sensor> sensorInformation) {
		this.sensorInformation = sensorInformation;
	}

	@Override
	public String toString() {
		return "SensorMetricsStatistics [sensorIdList=" + sensorIdList + ", metricsList=" + metricsList + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + ", sensorInformation=" + sensorInformation + "]";
	}

	
	
	
	
}
