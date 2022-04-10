package com.test.demo.validator;

import java.util.Date;  
import org.springframework.http.HttpHeaders;  
import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;  
import org.springframework.web.bind.MethodArgumentNotValidException;  
import org.springframework.web.bind.annotation.ControllerAdvice;  
import org.springframework.web.bind.annotation.ExceptionHandler;  
import org.springframework.web.bind.annotation.RestController;  
import org.springframework.web.context.request.WebRequest;  
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.test.demo.exception.ExceptionResponse;
import com.test.demo.exception.InvalidMetricNameException;
import com.test.demo.exception.NoSensorRegisteredException;
import com.test.demo.exception.SensorAlreadyRegisteredException;
import com.test.demo.exception.SensorNotRegisteredException;

@ControllerAdvice  
@RestController  
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler  
{  
	
	@ExceptionHandler(SensorAlreadyRegisteredException.class)  
	public final ResponseEntity<Object> handleSensorAlreadyRegisteredException(SensorAlreadyRegisteredException ex, WebRequest request)  
	{  
		System.out.println("handleSensorAlreadyRegisteredException");
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));  
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);  
	}  
	
	@ExceptionHandler(SensorNotRegisteredException.class)  
	public final ResponseEntity<Object> handleSensorAlreadyRegisteredException(SensorNotRegisteredException ex, WebRequest request)  
	{  
		System.out.println("handleSensorAlreadyRegisteredException");
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));  
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);  
	}
	
	@ExceptionHandler(InvalidMetricNameException.class)  
	public final ResponseEntity<Object> handleInvalidMetricNameExceptionException(InvalidMetricNameException ex, WebRequest request)  
	{  
		System.out.println("handleSensorAlreadyRegisteredException");
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));  
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);  
	}
	
	@ExceptionHandler(NoSensorRegisteredException.class)  
	public final ResponseEntity<Object> handleNoSensorRegisteredException(NoSensorRegisteredException ex, WebRequest request)  
	{  
		System.out.println("handleNoSensorRegisteredException");
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));  
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);  
	}
	
	@Override  
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)   
	{  
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());  
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);  
	} 
	
	@ExceptionHandler(Error.class)  
	public final ResponseEntity<Object> handleAllExceptions(Error ex, WebRequest request)  
	{  
		System.out.println("handleAllExceptions");
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), "Some error happened. Please try again later.", request.getDescription(false));  
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);  
	} 
}  