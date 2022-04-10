package com.test.demo.exception;

public class SensorAlreadyRegisteredException extends Throwable{

	public String getMessage() {
		return "The sensorId is already registered. Please use a different one.";
	}

}
