package com.test.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demo.exception.InvalidMetricNameException;
import com.test.demo.exception.NoSensorRegisteredException;
import com.test.demo.exception.SensorAlreadyRegisteredException;
import com.test.demo.exception.SensorNotRegisteredException;
import com.test.demo.model.Metrics;
import com.test.demo.model.Sensor;
import com.test.demo.model.SensorMetricsStatistics;
import com.test.demo.repository.SensorRepository;

@Service
public class SensorService {
	@Autowired
	private SensorRepository sensorRepository;

	public List<Sensor> getAllSensors() throws NoSensorRegisteredException {
		
		List<Sensor> sensorList=new ArrayList<Sensor>();
		sensorRepository.findAll()
						.forEach(sensorList::add);
		if(sensorList.size()==0) throw new NoSensorRegisteredException();
		return sensorList;
	}

	public Sensor getSensor(Integer sensorId) throws SensorNotRegisteredException {
		Optional<Sensor> sensors= sensorRepository.findById(sensorId);
		if(sensors.isEmpty())
			throw new SensorNotRegisteredException();
		
		return sensors.get();
	}

	public void addSesnor(Sensor sensor) throws SensorAlreadyRegisteredException {
		List<Metrics> metrics=new ArrayList<Metrics>();
		sensor.setMetrics(metrics);
		
		try {
			if (getSensor(sensor.getSensorId()) !=null)
				throw new SensorAlreadyRegisteredException();
		}catch (SensorNotRegisteredException e) {}
		
		sensorRepository.save(sensor);
	}

	public void updateSesnorMetrics(Sensor sensor) {
		sensorRepository.save(sensor);
	}

	public void deleteSensor(Integer sensorId) {
		sensorRepository.deleteById(sensorId);
	}

	public SensorMetricsStatistics querySesnorMetrics(SensorMetricsStatistics sensorMetricsStatistics) throws InvalidMetricNameException, SensorNotRegisteredException {
		sensorMetricsStatistics.setSensorInformation(new ArrayList<Sensor>());
		Date dateTo=sensorMetricsStatistics.getDateTo();
		Date dateFrom=sensorMetricsStatistics.getDateFrom();
		
		List<Integer> sensorIdList=sensorMetricsStatistics.getSensorIdList();
		
		//if no id is passed, calculate statistics for all the sensors listed
		if(sensorIdList==null || sensorIdList.size()==0)
			sensorIdList=sensorRepository.findAllSensorId();
		
		for(Integer sensorId:sensorIdList) {
			Sensor sensor= getSensor(sensorId);
			sensor.setMetrics(null);
			
			//check which metric are required to be calculated
			//if no date is passed or date are null, date checks are skipped
			if(sensorMetricsStatistics.getMetricsList().contains(Metrics.METRIC_HUMIDITY)) {
				sensor.setAverageHumidity(sensorRepository.averageHumidity(sensorId,dateFrom,dateTo));
			}
			if(sensorMetricsStatistics.getMetricsList().contains(Metrics.METRIC_TEMPERATURE)) {
				sensor.setAverageTemperature(sensorRepository.averageTemperature(sensorId,dateFrom,dateTo));
			}
			
			sensorMetricsStatistics.getSensorInformation().add(sensor);
		}
		
		return sensorMetricsStatistics;
	}
	
	
}
