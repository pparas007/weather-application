package com.test.demo.exception;

public class NoSensorRegisteredException extends Throwable{
	public String getMessage() {
		return "No sensor is registered at this moment.";
	}
}
