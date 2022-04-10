package com.test.demo.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.exception.InvalidMetricNameException;
import com.test.demo.exception.NoSensorRegisteredException;
import com.test.demo.exception.SensorAlreadyRegisteredException;
import com.test.demo.exception.SensorNotRegisteredException;
import com.test.demo.model.Metrics;
import com.test.demo.model.Sensor;
import com.test.demo.model.SensorMetricsStatistics;
import com.test.demo.service.SensorService;

@RestController
public class SensorController {
	
	@Autowired
	private SensorService sensorService;
	
	@GetMapping("/sensors")
	public List<Sensor> getAllSensors() throws NoSensorRegisteredException {
		return sensorService.getAllSensors();
	}
	
	@GetMapping("/sensors/{sensorId}")
	public Sensor getSensor(@PathVariable Integer sensorId) throws SensorNotRegisteredException {
		Sensor sensor=sensorService.getSensor(sensorId);
		if(sensor==null) 
			throw new SensorNotRegisteredException();
		return sensor;
	}
	
	@PostMapping("/sensors")
	public void addSesnor(@RequestBody Sensor sensor) throws SensorAlreadyRegisteredException {
		sensorService.addSesnor(sensor);
	}
	
	@PostMapping("/sensors/{sensorId}/metrics")
	public void updateSesnorMetrics(@PathVariable Integer sensorId, @RequestBody Metrics metrics) throws SensorNotRegisteredException {
		Sensor sensor=sensorService.getSensor(sensorId);
		
		if(sensor==null) 
			throw new SensorNotRegisteredException();
		
		metrics.setRecordedAt(new Date());
		sensor.getMetrics().add(metrics);
		
		sensorService.updateSesnorMetrics(sensor);
	}
	
	@DeleteMapping("/sensors/{sensorId}")
	public void deleteSensor(@PathVariable Integer sensorId) {
		sensorService.deleteSensor(sensorId);
	}
	
	@PostMapping("/sensors/metrics-statistics")
	public SensorMetricsStatistics querySesnorMetrics(@RequestBody SensorMetricsStatistics sensorMetricsStatistics) throws InvalidMetricNameException, SensorNotRegisteredException {
		return sensorService.querySesnorMetrics(sensorMetricsStatistics);
	}
}
