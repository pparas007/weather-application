package com.test.demo.exception;

public class InvalidMetricNameException extends Throwable {
	public String getMessage() {
		return "A metric's name is invalid. Please correct the name.";
	}
}
