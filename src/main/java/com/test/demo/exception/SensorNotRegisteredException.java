package com.test.demo.exception;

public class SensorNotRegisteredException extends Throwable {
	public String getMessage() {
		return "No sensor is registered under this id. Please use a valid id.";
	}
}
