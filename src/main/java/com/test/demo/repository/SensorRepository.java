package com.test.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.demo.model.Sensor;

public interface SensorRepository extends CrudRepository<Sensor, Integer>{
	@Query(value = "SELECT avg(metrics.temperature) FROM Sensor sensor"
					+ "    JOIN sensor.metrics metrics"
					+ "    WHERE (:dateFrom is null or metrics.recordedAt>= :dateFrom) "
					+ "    		AND (:dateTo is null or metrics.recordedAt<= :dateTo) "
					+ "    		AND sensor.sensorId = :sensorId ")
	public Double averageTemperature(@Param("sensorId") Integer sensorId,
			@Param("dateFrom") Date dateFrom,@Param("dateTo") Date dateTo);
	
	
	@Query(value = "SELECT avg(metrics.humidity) FROM Sensor sensor"
					+ "    JOIN sensor.metrics metrics"
					+ "    WHERE  	(:dateFrom is null or metrics.recordedAt>= :dateFrom) "
					+ "    		AND (:dateTo is null or metrics.recordedAt<= :dateTo) "
					+ "    		AND (sensor.sensorId = :sensorId) ")
	public Double averageHumidity(@Param("sensorId") Integer sensorId,
			@Param("dateFrom") Date dateFrom,@Param("dateTo") Date dateTo);
			
	@Query("select sensor.sensorId FROM Sensor sensor")
	List<Integer> findAllSensorId();
}
