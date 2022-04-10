package com.test.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.test.demo.controller.SensorController;
import com.test.demo.model.Sensor;

import ch.qos.logback.core.net.ObjectWriter;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;

@SpringBootTest
class DemoApplicationTests {
	
	@Autowired
	private SensorController controller;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	//@Autowired
	private MockMvc mockMvc;
	
	
	@BeforeEach
	public void setUp() {
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void contextLoads() throws Exception {
		Assertions.assertThat(controller).isNotNull();
	}
	
	@Test
	public void testWhenNoSensorIsRegistered() throws Exception {
		this.mockMvc.perform(get("/sensors")).andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.message").value("No sensor is registered at this moment."));
	}
	
	@Test
	public void testSensorRegistration() throws Exception {
	    Sensor sensor = new Sensor();
	    sensor.setSensorId(1);
	    sensor.setCityName("city");
	    sensor.setCountryName("country");

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    String requestJson=mapper.writeValueAsString(sensor);
	    
	    mockMvc.perform(post("/sensors")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(requestJson) 
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testDuplicateSensorRegistration() throws Exception {
	    Sensor sensor = new Sensor();
	    sensor.setSensorId(3);
	    sensor.setCityName("city");
	    sensor.setCountryName("country");

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    String requestJson=mapper.writeValueAsString(sensor);
	    
	    mockMvc.perform(post("/sensors")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(requestJson) 
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	    mockMvc.perform(post("/sensors")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(requestJson) 
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testFetchingSensorInformation() throws Exception {
	    Sensor sensor1 = new Sensor();
	    sensor1.setSensorId(2);
	    sensor1.setCityName("city");
	    sensor1.setCountryName("country");
	    
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    String requestJson1=mapper.writeValueAsString(sensor1);
	    
	    mockMvc.perform(post("/sensors")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(requestJson1) 
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	    
	    mockMvc.perform(get("/sensors/2")).andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.sensorId").value("2"));
	}
}
